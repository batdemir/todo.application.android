package com.batdemir.android.todolist.application.android.UI.Activities.MainSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.batdemir.android.todolist.application.android.API.Services.UserService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.UserModel;
import com.batdemir.android.todolist.application.android.GlobalVar.GlobalVariable;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.ButtonTools.OnTouchEvent;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.CharCountValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.ConfirmPasswordValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.EditTextTools.EmailValidWatcher;
import com.batdemir.android.todolist.application.android.Tools.ToolTimeExpressions;
import com.batdemir.android.todolist.application.android.UI.Activities.Base.BaseActivity;
import com.batdemir.android.todolist.application.android.UI.Activities.FirstSection.SignUpActivity;
import com.batdemir.android.todolist.application.android.databinding.ActivitySettingsBinding;

import java.util.Calendar;
import java.util.UUID;

import retrofit2.Response;

public class SettingsActivity extends BaseActivity implements UserService.UserServiceListener {

    private Context context;
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(false);
    }

    @Override
    public void getObjectReferences() {
        init_toolbar(true,getString(R.string.menu));
        context = this;
        binding = DataBindingUtil.setContentView((Activity) context,R.layout.activity_settings);
    }

    @Override
    public void loadData() {
        binding.editTextFirstName.setText(GlobalVariable.userModel.getFirstName());
        binding.editTextSurName.setText(GlobalVariable.userModel.getSurName());
        binding.editTextUserName.setText(GlobalVariable.userModel.getUserName());
        binding.editTextUserPassword.setText(GlobalVariable.userModel.getUserPassword());
        binding.editTextEmail.setText(GlobalVariable.userModel.getEmail());
    }

    @Override
    public void setListeners() {
        binding.editTextFirstName.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextFirstName));
        binding.editTextSurName.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextSurName));
        binding.editTextUserName.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextUserName));
        binding.editTextUserPassword.addTextChangedListener(new CharCountValidWatcher(5,binding.editTextUserPassword));
        binding.editTextConfirmUserPassword.addTextChangedListener(new ConfirmPasswordValidWatcher(binding.editTextUserPassword,binding.editTextConfirmUserPassword));
        binding.editTextEmail.addTextChangedListener(new EmailValidWatcher(binding.editTextEmail));
        binding.btnUpdate.setOnTouchListener(new OnTouchEvent(binding.btnUpdate));
        binding.btnDelete.setOnTouchListener(new OnTouchEvent(binding.btnDelete));

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(controlInputs()){
                    new UserService<>(context).Update(getUserModel());
                }
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserService<>(context).Delete(GlobalVariable.userModel);
            }
        });
    }

    //----functions----//

    private boolean controlInputs(){
        String message = "";
        message += binding.editTextFirstName.getError()!=null || binding.editTextFirstName.getText().toString().isEmpty()
                ?getString(R.string.please_enter_correctly)+ binding.textInputFirstName.getHint() + "!\n":"";
        message += binding.editTextSurName.getError()!=null || binding.editTextSurName.getText().toString().isEmpty()
                ?getString(R.string.please_enter_correctly)+ binding.textInputSurName.getHint() + "!\n":"";
        message += binding.editTextUserName.getError()!=null || binding.editTextUserName.getText().toString().isEmpty()
                ?getString(R.string.please_enter_correctly)+ binding.textInputUserName.getHint() + " !\n":"";
        message += binding.editTextUserPassword.getError()!=null || binding.editTextUserPassword.getText().toString().isEmpty()
                ?getString(R.string.please_enter_correctly)+ binding.textInputUserPassword.getHint() + "!\n":"";
        message += binding.editTextConfirmUserPassword.getError()!=null || binding.editTextConfirmUserPassword.getText().toString().isEmpty()
                ?getString(R.string.please_enter_correctly)+ binding.textInputConfirmUserPassword.getHint() + "!\n":"";
        message += binding.editTextEmail.getError()!=null || binding.editTextEmail.getText().toString().isEmpty()
                ?getString(R.string.please_enter_correctly)+ binding.textInputEmail.getHint() + "!\n":"";

        if(message.isEmpty())
            return true;
        else {
            ToolAlertDialog.newInstance(message,true,false).show(getSupportFragmentManager(), SignUpActivity.class.getSimpleName());
            return false;
        }
    }

    private UserModel getUserModel(){
        return new UserModel(
                GlobalVariable.userModel.getId(),
                binding.editTextUserName.getText().toString(),
                binding.editTextUserPassword.getText().toString(),
                binding.editTextFirstName.getText().toString(),
                binding.editTextSurName.getText().toString(),
                binding.editTextEmail.getText().toString(),
                Boolean.TRUE,
                new ToolTimeExpressions().setDateToString(Calendar.getInstance().getTime(), GlobalVariable.DateFormat.DEFAULT_DATE_FORMAT)
        );
    }

    @Override
    public void onSuccess(UserService.OperationType operationType, Response response) {
        switch (operationType){
            case Update:
                UserModel userModel = (UserModel) response.body();
                binding.editTextFirstName.setText(userModel.getFirstName());
                binding.editTextSurName.setText(userModel.getSurName());
                binding.editTextUserName.setText(userModel.getUserName());
                binding.editTextUserPassword.setText(userModel.getUserPassword());
                binding.editTextConfirmUserPassword.setText("");
                binding.editTextEmail.setText(userModel.getEmail());
                break;
            case Delete:
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                break;
        }
    }

    @Override
    public void onFailure(UserService.OperationType operationType) {

    }
}
