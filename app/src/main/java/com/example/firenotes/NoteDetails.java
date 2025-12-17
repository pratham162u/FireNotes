package com.example.firenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteDetails extends AppCompatActivity {

    EditText titleEditText, contentEditText;
    ImageButton saveBtn, backBtn, delBtn;
    TextView textDateTime;

    String docId;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        titleEditText = findViewById(R.id.notes_title_text);
        contentEditText = findViewById(R.id.notes_content_text);
        saveBtn = findViewById(R.id.save_note_btn);
        backBtn = findViewById(R.id.back_btn);
        delBtn = findViewById(R.id.delete_btn);
        textDateTime = findViewById(R.id.date_time_text);

        docId = getIntent().getStringExtra("docId");
        isEditMode = docId != null && !docId.isEmpty();

        titleEditText.setText(getIntent().getStringExtra("title"));
        contentEditText.setText(getIntent().getStringExtra("content"));

        textDateTime.setText(
                new SimpleDateFormat(
                        "EEE, dd MMMM | hh:mm a",
                        Locale.getDefault()
                ).format(new Date())
        );

        saveBtn.setEnabled(false);
        saveBtn.setImageAlpha(0x3F);
        delBtn.setVisibility(isEditMode ? View.VISIBLE : View.GONE);

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                boolean enable = !s.toString().trim().isEmpty();
                saveBtn.setEnabled(enable);
                saveBtn.setImageAlpha(enable ? 0xFF : 0x3F);
            }

            @Override public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}
            @Override public void afterTextChanged(Editable editable) {}
        });

        saveBtn.setOnClickListener(v -> saveNote());
        backBtn.setOnClickListener(v -> finish());
        delBtn.setOnClickListener(v -> deleteNoteFromFirebase());
    }


    void saveNote() {
        String title = titleEditText.getText().toString().trim();
        String content = contentEditText.getText().toString().trim();
        String time = textDateTime.getText().toString();

        if (title.isEmpty()) {
            titleEditText.setError("Title is required");
            return;
        }

        if (Utility.getCurrentUser() == null) {
            Utility.showToast(this, "Please login first");
            return;
        }

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setTime(time);

        saveNoteToFirebase(note);
    }

    void saveNoteToFirebase(Note note) {
        DocumentReference docRef;

        if (isEditMode) {
            docRef = Utility.getCollectionReferenceForNotes().document(docId);
        } else {
            docRef = Utility.getCollectionReferenceForNotes().document();
        }

        docRef.set(note)
                .addOnSuccessListener(unused -> {
                    Utility.showToast(this, "Note Saved Successfully ✅");
                    finish();
                })
                .addOnFailureListener(e -> {
                    Utility.showToast(this, "Save Failed: " + e.getMessage());
                });
    }
    
    void deleteNoteFromFirebase() {
        if (!isEditMode) return;

        Utility.getCollectionReferenceForNotes()
                .document(docId)
                .delete()
                .addOnSuccessListener(unused -> {
                    Utility.showToast(this, "Note Deleted 🗑️");
                    finish();
                })
                .addOnFailureListener(e -> {
                    Utility.showToast(this, "Delete Failed: " + e.getMessage());
                });
    }
}
