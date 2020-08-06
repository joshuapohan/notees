package com.jpohan.notees.contract;

import android.content.Intent;
import android.view.View;

import com.jpohan.notees.CreateNoteActivity;
import com.jpohan.notees.MainActivity;

public interface ActivityWithBottomToolbar {
    public void goToCreate(View view);
    public void goToList(View view);
    public void goToLogin(View view);
}
