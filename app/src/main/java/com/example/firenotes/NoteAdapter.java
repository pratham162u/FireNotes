package com.example.firenotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {

    private final Context context;

    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        // Null-safe binding
        holder.titleText.setText(note.getTitle() != null ? note.getTitle() : "No Title");
        holder.contentText.setText(note.getContent() != null ? note.getContent() : "No Content");
        holder.timeText.setText(note.getTime() != null ? note.getTime() : "Unknown Time");

        holder.itemView.setOnClickListener(v -> {
            try {
                String docId = this.getSnapshots().getSnapshot(position).getId();
                Intent intent = new Intent(context, NoteDetails.class);

                intent.putExtra("title", note.getTitle());
                intent.putExtra("content", note.getContent());
                intent.putExtra("time", note.getTime());
                intent.putExtra("docId", docId);

                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Error opening note: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_note_item, parent, false);
        return new NoteViewHolder(view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, contentText, timeText;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.note_title_textview);
            contentText = itemView.findViewById(R.id.note_content_textview);
            timeText = itemView.findViewById(R.id.note_time_textview);
        }
    }
}
