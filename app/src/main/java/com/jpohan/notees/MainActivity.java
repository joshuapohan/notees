package com.jpohan.notees;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.jpohan.notees.adapter.NoteListAdapter;
import com.jpohan.notees.adapter.decoration.SpacesItemDecoration;
import com.jpohan.notees.contract.ActivityWithBottomToolbar;
import com.jpohan.notees.database.DatabaseConnectionHelper;
import com.jpohan.notees.database.NotesTableHelper;

public class MainActivity extends AppCompatActivity implements ActivityWithBottomToolbar {
    Toolbar toolbar;
    RecyclerView listView;
    ImageView mainIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        mainIcon = findViewById(R.id.toolbar_icon_list);
        mainIcon.setImageResource(R.drawable.ic_action_list_selected);
        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.notes_list);
        final AppCompatActivity self = this;
        final NotesTableHelper helper = new NotesTableHelper(this);
        Thread init = new Thread(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = helper.getWritableDatabase();
                DatabaseConnectionHelper connection = DatabaseConnectionHelper.getInstance(db);
                NoteListAdapter adapter = new NoteListAdapter(self);
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                listView.setLayoutManager(layoutManager);
                listView.setAdapter(adapter);
                listView.addItemDecoration(new SpacesItemDecoration(10));
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

    public void goToLogin(View view) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("TEST_LOG","pausing");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("TEST_LOG","destroying");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        NoteListAdapter adapter = new NoteListAdapter(this);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);
        listView.addItemDecoration(new SpacesItemDecoration(10));
    }
}
