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
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.ConfirmPasswordValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.EditTextRules;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.EmailValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActions;
import com.batdemir.android.todolist.application.android.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements
        BaseActions,
        EditTextRules,
        ButtonRules {

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
    public void onBackPressed() {
        super.onBackPressed();
        new Tool(context).move(SignInActivity.class,true);
    }

    @Override
    public void getObjectReferences() {
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_sign_up);
        new Tool(context).anim(binding.linearProps);
    }

    @Override
    public void loadData() {
        defineEditTexts();
        defineButtons();
    }

    @Override
    public void setListeners() {
        binding.txtAlreadyHaveAnAccount.setOnClickListener(v -> new Tool(context).move(SignInActivity.class,true));
    }

    //----functions----//

    @Override
    public void defineEditTexts() {
        binding.editTextFirstName.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextFirstName));
        binding.editTextSurName.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextSurName));
        binding.editTextUserName.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextUserName));
        binding.editTextUserPassword.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextUserPassword));
        binding.editTextConfirmUserPassword.addTextChangedListener(new ConfirmPasswordValidWatcher(binding.editTextUserPassword,binding.editTextConfirmUserPassword));
        binding.editTextEmail.addTextChangedListener(new EmailValidWatcher(binding.editTextEmail));
    }

    @Override
    public void defineButtons() {
        binding.btnSignUp.setOnTouchListener(new OnTouchEvent(binding.btnSignUp));
    }
}
