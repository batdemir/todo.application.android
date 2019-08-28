package com.batdemir.android.todolist.application.android.UI.Activities.FirstSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.Services.UserService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.UserModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.ButtonTools.ButtonRules;
import com.batdemir.android.todolist.application.android.Tools.ButtonTools.OnTouchEvent;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.CharCountValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.ConfirmPasswordValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.EditTextRules;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.EmailValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActions;
import com.batdemir.android.todolist.application.android.databinding.ActivitySignUpBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.UUID;

import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements
        BaseActions,
        EditTextRules,
        ButtonRules,
        ToolAlertDialog.AlertClickListener,
        UserService.UserServiceListener {

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
        new Tool(context).move(SignInActivity.class,true,false);
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
        binding.btnSignUp.setOnClickListener(v -> {
            if(controlInputs())
                new UserService<>(context).Insert(getUserModel());
        });
        binding.txtAlreadyHaveAnAccount.setOnClickListener(v -> new Tool(context).move(SignInActivity.class,true,false));
    }

    //----functions----//

    private boolean controlInputs(){
        String message = "";
        message += binding.editTextFirstName.getError()!=null || binding.editTextFirstName.getText().toString().isEmpty()
                ?"Please Enter Correctly " + binding.editTextFirstName.getHint() + "!\n":"";
        message += binding.editTextSurName.getError()!=null || binding.editTextSurName.getText().toString().isEmpty()
                ?"Please Enter Correctly " + binding.editTextSurName.getHint() + "!\n":"";
        message += binding.editTextUserName.getError()!=null || binding.editTextUserName.getText().toString().isEmpty()
                ?"Please Enter Correctly " + binding.editTextUserName.getHint() + " !\n":"";
        message += binding.editTextUserPassword.getError()!=null || binding.editTextUserPassword.getText().toString().isEmpty()
                ?"Please Enter Correctly " + binding.editTextUserPassword.getHint() + "!\n":"";
        message += binding.editTextConfirmUserPassword.getError()!=null || binding.editTextConfirmUserPassword.getText().toString().isEmpty()
                ?"Please Enter Correctly " + binding.editTextConfirmUserPassword.getHint() + "!\n":"";
        message += binding.editTextEmail.getError()!=null || binding.editTextEmail.getText().toString().isEmpty()
                ?"Please Enter Correctly " + binding.editTextEmail.getHint() + "!\n":"";

        if(message.isEmpty())
            return true;
        else {
            ToolAlertDialog.newInstance(message,true,false).show(getSupportFragmentManager(),SignUpActivity.class.getSimpleName());
            return false;
        }
    }

    private UserModel getUserModel(){
        return new UserModel(
                UUID.randomUUID().toString(),
                binding.editTextUserName.getText().toString(),
                binding.editTextUserPassword.getText().toString(),
                binding.editTextFirstName.getText().toString(),
                binding.editTextSurName.getText().toString(),
                binding.editTextEmail.getText().toString(),
                new ToolTimeExpressions().setDateToString(Calendar.getInstance().getTime(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT)
        );
    }

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

    @Override
    public void alertOkey() {

    }

    @Override
    public void alertCancel() {

    }

    @Override
    public void onSuccess(Response response) {
        Snackbar.make(binding.rootSignIn ,"You have successfully registered. Please Login.",Snackbar.LENGTH_SHORT).show();
        new Tool(context).move(SignInActivity.class,true,false);
    }

    @Override
    public void onFailure() {

    }
}
