package com.batdemir.android.todolist.application.android.UI.Activities.FirstSection;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.Tools.ToolSharedPreferences;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity implements
        ToolAlertDialog.AlertClickListener {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = SplashActivity.this;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        check_permissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(permissions.length != 0){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 100) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED
                        && grantResults[4] == PackageManager.PERMISSION_GRANTED
                        && grantResults[5] == PackageManager.PERMISSION_GRANTED) {
                    move();
                } else {
                    if(!shouldShowRequestPermissionRationale(permissions[0])
                            ||!shouldShowRequestPermissionRationale(permissions[1])
                            ||!shouldShowRequestPermissionRationale(permissions[2])
                            ||!shouldShowRequestPermissionRationale(permissions[3])
                            ||!shouldShowRequestPermissionRationale(permissions[4])
                            ||!shouldShowRequestPermissionRationale(permissions[5])){
                        ToolAlertDialog
                                .newInstance(getString(R.string.please_active_permission)+getString(R.string.app_name)+getString(R.string.please_active_permission_continue),false,false)
                                .show(getSupportFragmentManager(),SplashActivity.class.getSimpleName());
                    }else{
                        try {
                            Thread.sleep(2000);
                            move();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    //----functions----//

    private void check_permissions(){
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.VIBRATE,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.ACCESS_WIFI_STATE
                    }, 100);
        }else{
            move();
        }
    }

    private void set_BASEURL() {
        ApiClient.BASE_URL = new ToolSharedPreferences(context).getSharedPreferencesString(ToolSharedPreferences.Keys.BASE_URL);
        ApiClient.retrofit = null;
        Log.d("BASE_URL=>\t",ApiClient.BASE_URL);
    }

    private void set_language(){
        Configuration configuration = context.getResources().getConfiguration();
        Locale locale = new Locale(new ToolSharedPreferences(context).getSharedPreferencesString(ToolSharedPreferences.Keys.language),new ToolSharedPreferences(context).getSharedPreferencesString(ToolSharedPreferences.Keys.country));
        configuration.setLocale(locale);
        context.getResources().updateConfiguration(configuration,context.getResources().getDisplayMetrics());
    }

    private void move(){
        set_BASEURL();
        set_language();
        new Tool(context).move(SignInActivity.class,true,false);
    }

    @Override
    public void alertOkey() {
        onStart();
    }

    @Override
    public void alertCancel() {
        onStart();
    }
}
