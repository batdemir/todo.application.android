package com.batdemir.android.todolist.application.android.Tools.EditTextTools;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class ConfirmPasswordValidWatcher implements TextWatcher {

    private String TAG = CharCountValidWatcher.class.getSimpleName();
    private final EditText parentEditText;
    private final EditText editText;

    public ConfirmPasswordValidWatcher(EditText parentEditText, EditText editText) {
        this.parentEditText = parentEditText;
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
        if(parentEditText.getText().toString().isEmpty()){
            return;
        }
        try {
            if(editText.getText().toString().isEmpty()){
                //TODO
            }else if(editText.getText().toString().equals(parentEditText.getText().toString())){
                editText.setError(null);
            }else {
                editText.setError("Input could not match.");
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
