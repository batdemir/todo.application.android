package com.batdemir.android.todolist.application.android.UI.Activities.Base;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements BaseActions {

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void init(){
        getObjectReferences();
        loadData();
        setListeners();
    }
}
