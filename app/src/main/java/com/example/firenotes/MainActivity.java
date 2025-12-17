package com.example.firenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    ImageButton addNoteBtn;
    RecyclerView recyclerView;
    ImageButton settingBtn;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if user is logged in and email verified
        if (FirebaseAuth.getInstance().getCurrentUser() == null ||
                !FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
            Toast.makeText(this, "Please login and verify email first", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        addNoteBtn = findViewById(R.id.add_note_btn);
        recyclerView = findViewById(R.id.recycler_view);
        settingBtn = findViewById(R.id.settings_btn);

        addNoteBtn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, NoteDetails.class)));

        settingBtn.setOnClickListener(v -> showMenu());

        setupRecyclerView();
    }

    void showMenu() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, settingBtn);
        popupMenu.getMenu().add("LogOut");
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(menuItem -> {
            if ("LogOut".equals(menuItem.getTitle())) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    void setupRecyclerView() {
        try {
            if (Utility.getCollectionReferenceForNotes() != null) {
                Query query = Utility.getCollectionReferenceForNotes()
                        .orderBy("time", Query.Direction.DESCENDING);

                FirestoreRecyclerOptions<Note> options =
                        new FirestoreRecyclerOptions.Builder<Note>()
                                .setQuery(query, Note.class)
                                .build();

                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                noteAdapter = new NoteAdapter(options, this);
                recyclerView.setAdapter(noteAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading notes: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (noteAdapter != null) noteAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (noteAdapter != null) noteAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (noteAdapter != null) noteAdapter.notifyDataSetChanged();
    }
}
