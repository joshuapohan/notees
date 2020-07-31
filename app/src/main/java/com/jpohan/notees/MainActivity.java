package com.jpohan.notees;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jpohan.notees.database.DatabaseConnectionHelper;
import com.jpohan.notees.database.NotesTableHelper;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        toolbar = findViewById(R.id.toolbar);
        final NotesTableHelper helper = new NotesTableHelper(this);
        Thread init = new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = helper.getWritableDatabase();
                DatabaseConnectionHelper connection = DatabaseConnectionHelper.getInstance(db);
            }
        });
        init.run();
    }

    public void goToCreate(View view) {
        Intent intent = new Intent(this, CreateNoteActivity.class);
        startActivity(intent);
    }

    public void goToList(View view) {
        // already in list view
    }
}
