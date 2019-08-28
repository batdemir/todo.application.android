package com.batdemir.android.todolist.application.android.UI.Activities.FirstSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.batdemir.android.todolist.application.android.API.Services.UserService;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.ButtonTools.ButtonRules;
import com.batdemir.android.todolist.application.android.Tools.ButtonTools.OnTouchEvent;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.CharCountValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.EditTextRules;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActions;
import com.batdemir.android.todolist.application.android.UI.Activities.MainSection.MenuActivity;
import com.batdemir.android.todolist.application.android.databinding.ActivitySignInBinding;
import com.batdemir.android.todolist.application.android.databinding.AlertDialogBinding;

import retrofit2.Response;

public class SignInActivity extends AppCompatActivity implements
        BaseActions,
        EditTextRules,
        ButtonRules,
        ToolAlertDialog.AlertClickListener,
        UserService.UserServiceListener {

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
        ToolAlertDialog
                .newInstance(getString(R.string.exit_application),true,true)
                .show(getSupportFragmentManager(),SignInActivity.class.getSimpleName());
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
        binding.btnLogin.setOnClickListener(v -> {
            if(controlInputs())
                new UserService<>(context).Get();
        });
        binding.btnSignUp.setOnClickListener(v -> new Tool(context).move(SignUpActivity.class,false,false));
    }

    //----functions----//

    private boolean controlInputs(){
        String message = "";
        message += binding.editTextUserName.getError()!=null || binding.editTextUserName.getText().toString().isEmpty()
                ?"Please Enter Correctly " + binding.textInputUserName.getHint() + "!\n":"";
        message += binding.editTextUserPassword.getError()!=null || binding.editTextUserPassword.getText().toString().isEmpty()
                ?"Please Enter Correctly " + binding.textInputUserPassword.getHint() + "!\n":"";

        if(message.isEmpty())
            return true;
        else {
            ToolAlertDialog.newInstance(message,true,false).show(getSupportFragmentManager(),SignInActivity.class.getSimpleName());
            return false;
        }
    }

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

    @Override
    public void alertOkey() {

    }

    @Override
    public void alertCancel() {

    }

    @Override
    public void onSuccess(UserService.OperationType operationType, Response response) {
        switch (operationType){
            case Get:
                new Tool(context).move(MenuActivity.class,true,false);
                break;
            case GetByName:
                break;
            case Insert:
                break;
            case Update:
                break;
            case Delete:
                break;
        }
    }

    @Override
    public void onFailure() {

    }
}
