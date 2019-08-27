package com.batdemir.android.todolist.application.android.UI.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.Tools.Tool;

public class SplashActivity extends AppCompatActivity {

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
        //check_permissions();
        move();
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
                        && grantResults[5] == PackageManager.PERMISSION_GRANTED
                        && grantResults[6] == PackageManager.PERMISSION_GRANTED
                        && grantResults[7] == PackageManager.PERMISSION_GRANTED
                        && grantResults[8] == PackageManager.PERMISSION_GRANTED
                        && grantResults[9] == PackageManager.PERMISSION_GRANTED) {
                    move();
                } else {
                    if(!shouldShowRequestPermissionRationale(permissions[0])
                            ||!shouldShowRequestPermissionRationale(permissions[1])
                            ||!shouldShowRequestPermissionRationale(permissions[2])
                            ||!shouldShowRequestPermissionRationale(permissions[3])
                            ||!shouldShowRequestPermissionRationale(permissions[4])
                            ||!shouldShowRequestPermissionRationale(permissions[5])
                            ||!shouldShowRequestPermissionRationale(permissions[6])
                            ||!shouldShowRequestPermissionRationale(permissions[7])
                            ||!shouldShowRequestPermissionRationale(permissions[8])
                            ||!shouldShowRequestPermissionRationale(permissions[9])){
                        //AlertDialogHelper.show_alertDialogForPermission(context,dialog);
                    }else{
                        Toast.makeText(this, "Devam etmek için izinler kabul edildi.", Toast.LENGTH_LONG).show();
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
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.BLUETOOTH_ADMIN,
                            Manifest.permission.BLUETOOTH,
                            Manifest.permission.VIBRATE},
                    100);
        }else{
            move();
        }
    }

    private void set_BASEURL() {
        //ApiClient.BASE_URL = SharedPreferencesHelper.get_sharedPreferencesString(context, SharedPreferencesHelper.SharedPreferencesKeys.baseUrl);
        ApiClient.retrofit = null;
        Log.d("BASEURL",ApiClient.BASE_URL);
    }

    private void move(){
        set_BASEURL();
        new Tool(context).move(SignInActivity.class,false);
    }
}
