package com.jpohan.notees.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.jpohan.notees.R;
import com.jpohan.notees.ViewNoteActivity;
import com.jpohan.notees.helper.NotesObjectHelper;
import com.jpohan.notees.model.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {
    public static final String NOTE_VIEW_MESSAGE = "com.jpohan.notees.note_view_message";
    public List<Note> notes;
    private Context context;

    public NoteListAdapter(Context context){
        this.context = context;
        notes = NotesObjectHelper.getListOfNotes();
    }

    @NonNull
    @Override
    public NoteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.note_card, parent, false);
        return new NoteListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListViewHolder holder, final int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.content.setText(notes.get(position).getContent());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewNoteActivity.class);
                intent.putExtra(NOTE_VIEW_MESSAGE, notes.get(position).getUuid());
                AppCompatActivity activity = (AppCompatActivity) context;
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    public class NoteListViewHolder extends RecyclerView.ViewHolder {
        private TextView title, content;
        ConstraintLayout container;

        public NoteListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_card_title);
            content = itemView.findViewById(R.id.note_card_content);
            container = itemView.findViewById(R.id.note_card_container);
        }
    }
}
