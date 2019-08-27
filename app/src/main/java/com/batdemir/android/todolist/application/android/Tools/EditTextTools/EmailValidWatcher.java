package com.batdemir.android.todolist.application.android.Tools.EditTextTools;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class EmailValidWatcher implements TextWatcher {

    private String TAG = EmailValidWatcher.class.getSimpleName();
    private final EditText editText;

    public EmailValidWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            if(editText.getText().toString().isEmpty()){
                //TODO
            }else if(new ToolEditText().isEmailValid(editText.getText().toString())){
                editText.setError(null);
            }else {
                editText.setError("Input could not be match e-mail format.");
            }
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
}
