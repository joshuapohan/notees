package com.jpohan.notees;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.jpohan.notees.contract.ActivityWithBottomToolbar;
import com.jpohan.notees.database.DatabaseConnectionHelper;
import com.jpohan.notees.model.contract.NoteContract;

import java.util.UUID;

public class CreateNoteActivity extends AppCompatActivity implements ActivityWithBottomToolbar {
    EditText title;
    EditText content;
    ImageView mainIcon;
    SQLiteDatabase db;
    Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note_view);
        // update toolbar icon
        mainIcon = findViewById(R.id.toolbar_icon_new);
        mainIcon.setImageResource(R.drawable.ic_action_new_selected);

        title = findViewById(R.id.edit_title);
        content = findViewById(R.id.note_content);
        saveButton = findViewById(R.id.update_note_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNote(view);
            }
        });
        db = DatabaseConnectionHelper.getInstance().getConnection();
    }

    public void goToCreate(View view) {
        // already in create note view
    }

    public void goToList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void createNote(View view){
        // Store in DB
        UUID uuid = UUID.randomUUID();
        String notesUUID = uuid.toString();
        String notesTitle = title.getText().toString();
        String notesContent = content.getText().toString();
        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteContractEntry.COLUMN_NAME_UUID, notesUUID);
        values.put(NoteContract.NoteContractEntry.COLUMN_NAME_TITLE, notesTitle);
        values.put(NoteContract.NoteContractEntry.COLUMN_NAME_CONTENT, notesContent);
        long rowId = db.insert(NoteContract.NoteContractEntry.TABLE_NAME, null, values);
        title.getText().clear();
        content.getText().clear();
        Snackbar succcess = Snackbar.make(view, "Note Created", Snackbar.LENGTH_LONG);
        succcess.show();
    }
}
