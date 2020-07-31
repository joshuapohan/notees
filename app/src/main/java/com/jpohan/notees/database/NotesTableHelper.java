package com.jpohan.notees.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jpohan.notees.model.contract.NoteContract;

public class NotesTableHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_NOTES_TABLE_VERSION_1 = "CREATE TABLE "
        + NoteContract.NoteContractEntry.TABLE_NAME + " ("
        + NoteContract.NoteContractEntry.COLUMN_NAME_UUID + " VARCHAR(40) PRIMARY KEY, "
        + NoteContract.NoteContractEntry.COLUMN_NAME_TITLE + " TEXT, "
        + NoteContract.NoteContractEntry.COLUMN_NAME_CONTENT + " TEXT);";

    public static final String DATABASE_NAME = "notees";
    public static final int DATABASE_VERSION = 1;

    public NotesTableHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_NOTES_TABLE_VERSION_1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
