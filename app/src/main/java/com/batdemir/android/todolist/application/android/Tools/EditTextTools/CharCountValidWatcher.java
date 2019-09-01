package com.batdemir.android.todolist.application.android.Tools.EditTextTools;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.batdemir.android.todolist.application.android.R;

import java.lang.reflect.Field;

public class CharCountValidWatcher implements TextWatcher {

    private String TAG = CharCountValidWatcher.class.getSimpleName();
    private final int count;
    private final EditText editText;

    public CharCountValidWatcher(int count, EditText editText) {
        this.count = count;
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
            } else if (editText.getText().toString().length() >= count) {
                editText.setError(null);
            } else {
                editText.setError("Input could not be lower than " + String.valueOf(count) + " character.");
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
