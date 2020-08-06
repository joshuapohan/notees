package com.jpohan.notees;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jpohan.notees.contract.ActivityWithBottomToolbar;
import com.jpohan.notees.task.LoginTask;

public class AccountActivity extends AppCompatActivity implements ActivityWithBottomToolbar {

    ImageView mainIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        mainIcon = findViewById(R.id.toolbar_icon_account);
        mainIcon.setImageResource(R.drawable.ic_action_account_selected);
    }

    public void goToCreate(View view) {
        Intent intent = new Intent(this, CreateNoteActivity.class);
        startActivity(intent);
    }

    public void goToList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToLogin(View view) {
    }

    public void login(View view){
        TextView user = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);
        LoginTask task = new LoginTask(user.getText().toString(), password.getText().toString());
        task.execute();
    }
}
