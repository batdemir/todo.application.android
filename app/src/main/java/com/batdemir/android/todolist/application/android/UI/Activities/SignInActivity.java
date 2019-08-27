package com.batdemir.android.todolist.application.android.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.ButtonTools.ButtonRules;
import com.batdemir.android.todolist.application.android.Tools.ButtonTools.OnTouchEvent;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.CharCountValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.EditTextRules;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActions;
import com.batdemir.android.todolist.application.android.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity implements
        BaseActions,
        EditTextRules,
        ButtonRules {

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
    public void onBackPressed() {
        //TODO
    }

    @Override
    public void getObjectReferences() {
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_sign_in);
        new Tool(context).anim(binding.linearProps);
    }

    @Override
    public void loadData() {
        defineEditTexts();
        defineButtons();
    }

    @Override
    public void setListeners() {
        binding.btnSignUp.setOnClickListener(v -> new Tool(context).move(SignUpActivity.class,false));
    }

    //----functions----//

    @Override
    public void defineEditTexts() {
        binding.editTextUserName.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextUserName));
        binding.editTextUserPassword.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextUserPassword));
    }

    @Override
    public void defineButtons() {
        binding.btnLogin.setOnTouchListener(new OnTouchEvent(binding.btnLogin));
        binding.btnSignUp.setOnTouchListener(new OnTouchEvent(binding.btnSignUp));
    }
}
