package com.jpohan.notees.database;

import android.database.sqlite.SQLiteDatabase;

public class DatabaseConnectionHelper {
    private static DatabaseConnectionHelper instance;
    private SQLiteDatabase db;

    private DatabaseConnectionHelper(SQLiteDatabase db){
        this.db = db;
    }

    public SQLiteDatabase getConnection(){
        if(instance != null){
            return instance.db;
        }
        return null;
    }

    public static DatabaseConnectionHelper getInstance(SQLiteDatabase db){
        if(instance == null){
            instance =  new DatabaseConnectionHelper(db);
        }
        return instance;
    }

    public static DatabaseConnectionHelper getInstance(){
        return instance;
    }
}
