package com.jpohan.notees.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jpohan.notees.database.DatabaseConnectionHelper;
import com.jpohan.notees.model.Note;
import com.jpohan.notees.model.contract.NoteContract;

import java.util.ArrayList;
import java.util.List;

public class NotesObjectHelper {
    public static List<Note> getListOfNotes(){
        SQLiteDatabase db = DatabaseConnectionHelper.getInstance().getConnection();
        String[] projection = {
                "uuid",
                "title",
                "content"
        };
        Cursor cursor = db.query(
                NoteContract.NoteContractEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        List<Note> notes = new ArrayList<>();
        while(cursor.moveToNext()){
            Note note = new Note();
            String uuid = cursor.getString(cursor.getColumnIndex(NoteContract.NoteContractEntry.COLUMN_NAME_UUID));
            String title = cursor.getString(cursor.getColumnIndex(NoteContract.NoteContractEntry.COLUMN_NAME_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(NoteContract.NoteContractEntry.COLUMN_NAME_CONTENT));
            note.setTitle(title);
            note.setContent(content);
            note.setUuid(uuid);
            notes.add(note);
        }
        return notes;
    }
}
