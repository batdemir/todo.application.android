package com.batdemir.android.todolist.application.android.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ToolSharedPreferences {

    public enum Keys{
        BASE_URL("http://alsancak.org/api/"),
        userName(""),
        userPassword(""),
        language("en"),
        country("US");

        private String defValue;

        Keys(String defValue) {
            this.defValue = defValue;
        }

        @Override
        public String toString() {
            return defValue;
        }
    }

    private String TAG=ToolSharedPreferences.class.getSimpleName();
    private Context context;

    public ToolSharedPreferences(Context context) {
        this.context = context;
    }

    public void setSharedPreferencesInteger(Keys key, Integer value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(String.valueOf(key),value);
        editor.commit();
    }

    public Integer getSharedPreferencesInteger(Keys key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Integer status = preferences.getInt(String.valueOf(key), Integer.parseInt(key.defValue));
        return status;
    }

    public void setSharedPreferencesString(Keys key, String value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(String.valueOf(key),value);
        editor.commit();
    }

    public String getSharedPreferencesString(Keys key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String status = preferences.getString(String.valueOf(key),key.defValue);
        return status;
    }

    public void setSharedPreferencesBoolean(Keys key, Boolean value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(String.valueOf(key),value);
        editor.commit();
    }

    public Boolean getSharedPreferencesBoolean(Keys key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Boolean status = preferences.getBoolean(String.valueOf(key), Boolean.parseBoolean(key.defValue));
        return status;
    }

    public void setSharedPreferencesLong(Keys key, Long value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(String.valueOf(key),value);
        editor.commit();
    }

    public Long getSharedPreferencesLong(Keys key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Long status = preferences.getLong(String.valueOf(key), Long.parseLong(key.defValue));
        return status;
    }

    public void setSharedPreferencesFloat(Keys key, Float value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(String.valueOf(key),value);
        editor.commit();
    }

    public Float getSharedPreferencesFloat(Keys key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Float status = preferences.getFloat(String.valueOf(key), Float.parseFloat(key.defValue));
        return status;
    }
}
