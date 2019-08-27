package com.batdemir.android.todolist.application.android.Tools.EditTextTools;

import android.util.Log;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolEditText {

    private String TAG = ToolEditText.class.getSimpleName();

    public boolean isEmailValid(String email){
        try{
            String regExpn =
                    "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                            +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                            +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                            +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                            +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                            +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

            Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);

            return matcher.matches();
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
            return false;
        }
    }

    public boolean isPhoneNumberValid(String phoneNumber, String countryCode){
        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, countryCode);
            return phoneUtil.isValidNumber(numberProto);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
        return false;
    }
}
