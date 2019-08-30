package com.batdemir.android.todolist.application.android.UI.Activities.FirstSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;

import com.batdemir.android.todolist.application.android.API.Services.UserService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.UserModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.ButtonTools.OnTouchEvent;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.CharCountValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.Tools.ToolSharedPreferences;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActions;
import com.batdemir.android.todolist.application.android.UI.Activities.MainSection.MenuActivity;
import com.batdemir.android.todolist.application.android.databinding.ActivitySignInBinding;

import retrofit2.Response;

public class SignInActivity extends AppCompatActivity implements
        BaseActions,
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
        if(new ToolSharedPreferences(context).getSharedPreferencesString(ToolSharedPreferences.Keys.rememberMe).isEmpty()){
            binding.editTextUserName.setText("");
            binding.checkBoxRememberMe.setChecked(false);
        }else {
            binding.editTextUserName.setText(new ToolSharedPreferences(context).getSharedPreferencesString(ToolSharedPreferences.Keys.rememberMe));
            binding.checkBoxRememberMe.setChecked(true);
            binding.editTextUserPassword.requestFocus();
        }
    }

    @Override
    public void setListeners() {
        binding.editTextUserName.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextUserName));
        binding.editTextUserPassword.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextUserPassword));
        binding.btnLogin.setOnTouchListener(new OnTouchEvent(binding.btnLogin));
        binding.btnSignUp.setOnTouchListener(new OnTouchEvent(binding.btnSignUp));

        binding.btnLogin.setOnClickListener(v -> {
            if(controlInputs()){
                if(binding.checkBoxRememberMe.isChecked())
                    new ToolSharedPreferences(context).setSharedPreferencesString(ToolSharedPreferences.Keys.rememberMe,binding.editTextUserName.getText().toString());
                new UserService<>(context).Login(binding.editTextUserName.getText().toString(), binding.editTextUserPassword.getText().toString());
            }
        });
        binding.btnSignUp.setOnClickListener(v -> new Tool(context).move(SignUpActivity.class,false,false));
    }

    //----functions----//

    private boolean controlInputs(){
        String message = "";
        message += binding.editTextUserName.getError()!=null || binding.editTextUserName.getText().toString().isEmpty()
                ?getString(R.string.please_enter_correctly)+ binding.textInputUserName.getHint() + "!\n":"";
        message += binding.editTextUserPassword.getError()!=null || binding.editTextUserPassword.getText().toString().isEmpty()
                ?getString(R.string.please_enter_correctly)+ binding.textInputUserPassword.getHint() + "!\n":"";

        if(message.isEmpty())
            return true;
        else {
            ToolAlertDialog.newInstance(message,true,false).show(getSupportFragmentManager(),SignInActivity.class.getSimpleName());
            return false;
        }
    }

    @Override
    public void onSuccess(UserService.OperationType operationType, Response response) {
        switch (operationType){
            case Login:
                GlobalVariable.userModel = (UserModel) response.body();
                new Tool(context).move(MenuActivity.class,true,false);
                break;
        }
    }

    @Override
    public void onFailure(UserService.OperationType operationType) {

    }

    @Override
    public void alertOkey() {

    }

    @Override
    public void alertCancel() {

    }
}
