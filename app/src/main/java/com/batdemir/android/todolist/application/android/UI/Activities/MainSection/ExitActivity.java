package com.batdemir.android.todolist.application.android.UI.Activities.MainSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.databinding.ActivityExitBinding;

public class ExitActivity extends BaseActivity {

    private Context context;
    private ActivityExitBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false);
    }

    @Override
    public void getObjectReferences() {
        init_toolbar(true,getString(R.string.menu));
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_exit);
    }

    @Override
    public void loadData() {
        finish();
    }

    @Override
    public void setListeners() {

    }
}
