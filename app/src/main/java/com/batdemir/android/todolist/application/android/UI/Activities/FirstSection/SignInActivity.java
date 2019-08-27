package com.batdemir.android.todolist.application.android.UI.Activities.FirstSection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

public class SignInActivity extends AppCompatActivity implements
        BaseActions,
        EditTextRules,
        ButtonRules,
        ToolAlertDialog.clickOkey,
        ToolAlertDialog.clickCancel {

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
                .newInstance(getString(R.string.exit_application),true)
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
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolAlertDialog.newInstance("Test",false).show(getSupportFragmentManager(),"test");
            }
        });
        binding.btnSignUp.setOnClickListener(v -> new Tool(context).move(SignUpActivity.class,false,false));
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

    @Override
    public void dialogOkey() {
        Toast.makeText(context,"Okey",Toast.LENGTH_SHORT).show();
        new Tool(context).move(MenuActivity.class,true,false);
    }

    @Override
    public void dialogCancel() {
        Toast.makeText(context,"Cancel",Toast.LENGTH_SHORT).show();
    }
}
