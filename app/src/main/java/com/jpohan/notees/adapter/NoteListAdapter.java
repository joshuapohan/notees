package com.jpohan.notees.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jpohan.notees.R;
import com.jpohan.notees.helper.NotesObjectHelper;
import com.jpohan.notees.model.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {
    public List<Note> notes;

    public NoteListAdapter(){
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
    public void onBindViewHolder(@NonNull NoteListViewHolder holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.content.setText(notes.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    public class NoteListViewHolder extends RecyclerView.ViewHolder {
        private TextView title, content;

        public NoteListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_card_title);
            content = itemView.findViewById(R.id.note_card_content);
        }
    }
}
