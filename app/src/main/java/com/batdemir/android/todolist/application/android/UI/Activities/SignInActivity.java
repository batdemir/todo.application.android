package com.batdemir.android.todolist.application.android.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActions;
import com.batdemir.android.todolist.application.android.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity implements BaseActions {

    private Context context;
    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getObjectReferences();
        loadData();
        setListeners();
    }

    @Override
    public void getObjectReferences() {
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_sign_in);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void setListeners() {
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.move(context,SignUpActivity.class,false);
            }
        });
    }
}
