package com.jpohan.notees.model.contract;

import android.provider.BaseColumns;

public class NoteContract {
    private NoteContract(){};

    public static class NoteContractEntry implements BaseColumns{
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_UUID = "uuid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
    }
}
