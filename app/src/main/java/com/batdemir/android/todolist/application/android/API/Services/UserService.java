package com.batdemir.android.todolist.application.android.API.Services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.IUserService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.UserModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class UserService<T> {

    private Context context;

    public UserService(Context context) {
        this.context = context;
    }

    public void Get(){
        IUserService iUserService = ApiClient.getClient().create(IUserService.class);
        new ConnectService(context).execute(iUserService.Get());
    }

    public void GetByName(String name){
        IUserService iUserService = ApiClient.getClient().create(IUserService.class);
        new ConnectService(context).execute(iUserService.GetByName(name));
    }

    public void Insert(UserModel userModel){
        IUserService iUserService = ApiClient.getClient().create(IUserService.class);
        new ConnectService(context).execute(iUserService.Insert(userModel));
    }

    public void Update(String name, UserModel userModel){
        IUserService iUserService = ApiClient.getClient().create(IUserService.class);
        new ConnectService(context).execute(iUserService.Update(name, userModel));
    }

    public void Delete(String name){
        IUserService iUserService = ApiClient.getClient().create(IUserService.class);
        new ConnectService(context).execute(iUserService.Delete(name));
    }

    @SuppressLint("StaticFieldLeak")
    private class ConnectService extends AsyncTask<Call,Void, Response<T>>{
        private ProgressDialog progressDialog;
        private Context context;

        public ConnectService(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Please wait.");
                progressDialog.show();
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
            }
        }

        @Override
        protected Response<T> doInBackground(Call... calls) {
            try{
                return calls[0].execute();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Response<T> response) {
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            Activity activity  = (Activity) context;
            UserServiceListener userServiceListener = (UserServiceListener) activity;
            try{
                if(response.isSuccessful()){
                    userServiceListener.onSuccess(response);
                }else {
                    Toast.makeText(context,response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    userServiceListener.onFailure();
                }
            }catch (Exception e){
                Toast.makeText(context,"Could not contact the service.\nPlease Try Again.",Toast.LENGTH_SHORT).show();
                userServiceListener.onFailure();
            }
        }
    }

    public interface UserServiceListener<T>{
        void onSuccess(Response<T> response);
        void onFailure();
    }
}
