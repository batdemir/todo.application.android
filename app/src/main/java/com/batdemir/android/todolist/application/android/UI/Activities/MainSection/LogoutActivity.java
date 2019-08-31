package com.batdemir.android.todolist.application.android.UI.Activities.MainSection;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.Tools.ToolSharedPreferences;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.FirstSection.SplashActivity;
import com.batdemir.android.todolist.application.android.databinding.ActivityLogoutBinding;

public class LogoutActivity extends BaseActivity {

    private Context context;
    private ActivityLogoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false);
    }

    @Override
    public void getObjectReferences() {
        init_toolbar(true,getString(R.string.menu));
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_logout);
    }

    @Override
    public void loadData() {
        new ToolSharedPreferences(context).setSharedPreferencesString(ToolSharedPreferences.Keys.userName,"");
        new ToolSharedPreferences(context).setSharedPreferencesString(ToolSharedPreferences.Keys.userPassword,"");
        new Tool(context).move(SplashActivity.class,true,false);
    }

    @Override
    public void setListeners() {

    }
}
