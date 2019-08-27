package com.batdemir.android.todolist.application.android.Tools.EditTextTools;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.Locale;

public class PhoneValidWatcher implements TextWatcher {

    private String TAG = PhoneValidWatcher.class.getSimpleName();
    private final EditText editText;

    public PhoneValidWatcher(EditText editText) {
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
            if (editText.getText().toString().isEmpty()) {
                //TODO
            } else if (new ToolEditText().isPhoneNumberValid(editText.getText().toString(), Locale.getDefault().getCountry())) {
                editText.setError(null);
            } else {
                editText.setError("Input could not be match phone format.");
            }
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }
}
