package com.batdemir.android.todolist.application.android.UI.Activities.Base;

import android.graphics.drawable.ColorDrawable;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;

public abstract class BaseActivity extends AppCompatActivity implements
        BaseActions,
        ToolAlertDialog.AlertClickListener{

    private boolean showExitDialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (showExitDialog){
            ToolAlertDialog
                    .newInstance(getString(R.string.exit_application),true,true)
                    .show(getSupportFragmentManager(),BaseActivity.class.getSimpleName());
        }else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void init(boolean showExitDialog){
        this.showExitDialog = showExitDialog;
        getObjectReferences();
        loadData();
        setListeners();
    }

    public void init_toolbar(boolean showHomeButton, String title){
        getSupportActionBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setElevation(16F);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primaryColor,null)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeButton);
    }

    @Override
    public void alertOkey() {
        if(showExitDialog){
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    @Override
    public void alertCancel() {
        //TODO
    }
}
