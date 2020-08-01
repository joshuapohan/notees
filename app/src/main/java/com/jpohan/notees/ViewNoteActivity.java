package com.jpohan.notees;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.jpohan.notees.adapter.NoteListAdapter;
import com.jpohan.notees.helper.NotesObjectHelper;
import com.jpohan.notees.model.Note;

public class ViewNoteActivity extends AppCompatActivity {
    EditText title;
    EditText content;
    Button deleteButton;
    Button updateButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_view);
        Intent intent = getIntent();
        final String uuid = intent.getStringExtra(NoteListAdapter.NOTE_VIEW_MESSAGE);
        Note note = NotesObjectHelper.getByUUID(uuid);
        title = findViewById(R.id.edit_title);
        content = findViewById(R.id.note_content);
        title.setText(note.getTitle());
        content.setText(note.getContent());
        deleteButton = findViewById(R.id.delete_note_button);
        updateButton = findViewById(R.id.update_note_button);
        final AppCompatActivity self = this;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesObjectHelper.deleteById(uuid);
                Intent intent = new Intent(self, MainActivity.class);
                self.startActivity(intent);
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note();
                note.setUuid(uuid);
                note.setTitle(title.getText().toString());
                note.setContent(content.getText().toString());
                NotesObjectHelper.update(note);
                Snackbar succcess = Snackbar.make(view, "Note Updated", Snackbar.LENGTH_LONG);
                succcess.show();
            }
        });
    }

    public void goToCreate(View view) {
        Intent intent = new Intent(this, CreateNoteActivity.class);
        startActivity(intent);
    }

    public void goToList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
