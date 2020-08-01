package com.jpohan.notees.helper;

import android.content.ContentValues;
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

    public static Note getByUUID(String uuid){
        SQLiteDatabase db = DatabaseConnectionHelper.getInstance().getConnection();
        String[] projection = {
                "uuid",
                "title",
                "content"
        };
        String selection = NoteContract.NoteContractEntry.COLUMN_NAME_UUID + " = ?";
        String[] selectionArgs = {uuid};
        Cursor cursor = db.query(
                NoteContract.NoteContractEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Note note = new Note();
        if(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(NoteContract.NoteContractEntry.COLUMN_NAME_TITLE));
            String content = cursor.getString(cursor.getColumnIndex(NoteContract.NoteContractEntry.COLUMN_NAME_CONTENT));
            note.setTitle(title);
            note.setContent(content);
            note.setUuid(uuid);
        }
        return note;
    }

    public static void deleteById(String uuid){
        SQLiteDatabase db = DatabaseConnectionHelper.getInstance().getConnection();
        String[] args = {uuid};
        db.delete(NoteContract.NoteContractEntry.TABLE_NAME, "uuid = ?", args);
    }

    public static void update(Note note){
        SQLiteDatabase db = DatabaseConnectionHelper.getInstance().getConnection();
        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteContractEntry.COLUMN_NAME_TITLE, note.getTitle());
        values.put(NoteContract.NoteContractEntry.COLUMN_NAME_CONTENT, note.getContent());
        String[] args = {note.getUuid()};
        db.update(NoteContract.NoteContractEntry.TABLE_NAME, values, "uuid = ?", args);
    }
}
