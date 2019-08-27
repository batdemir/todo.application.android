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
import com.batdemir.android.todolist.application.android.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements BaseActions {

    private Context context;
    private ActivitySignUpBinding binding;

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
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_sign_up);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void setListeners() {
        binding.txtAlreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.move(context,SignInActivity.class,true);
            }
        });
    }
}
