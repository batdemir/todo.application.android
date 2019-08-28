package com.batdemir.android.todolist.application.android.API.Services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.IUserService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.UserModel;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.Tool;
import com.batdemir.android.todolist.application.android.Tools.ToolConnection;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class UserService<T> implements
        ToolAlertDialog.AlertClickListener {

    private Context context;
    private boolean connectionAvailable = false;

    public enum OperationType{
        Get,
        GetByName,
        Insert,
        Update,
        Delete;

        OperationType() {
        }
    }

    public UserService(Context context) {
        this.context = context;
        connectionAvailable = ToolConnection.isConnected(context);
        if(!connectionAvailable)
            ToolAlertDialog
                    .newInstance("Please Check Your Internet Connection.",false,false)
                    .show(((FragmentActivity) context).getSupportFragmentManager(),UserService.class.getSimpleName());
    }

    public void Get(){
        if(!connectionAvailable)
            return;
        IUserService iUserService = ApiClient.getClient().create(IUserService.class);
        new ConnectService(context,OperationType.Get).execute(iUserService.Get());
    }

    public void GetByName(String name){
        if(!connectionAvailable)
            return;
        IUserService iUserService = ApiClient.getClient().create(IUserService.class);
        new ConnectService(context,OperationType.GetByName).execute(iUserService.GetByName(name));
    }

    public void Insert(UserModel userModel){
        if(!connectionAvailable)
            return;
        IUserService iUserService = ApiClient.getClient().create(IUserService.class);
        new ConnectService(context,OperationType.Insert).execute(iUserService.Insert(userModel));
    }

    public void Update(String name, UserModel userModel){
        if(!connectionAvailable)
            return;
        IUserService iUserService = ApiClient.getClient().create(IUserService.class);
        new ConnectService(context,OperationType.Update).execute(iUserService.Update(name, userModel));
    }

    public void Delete(String name){
        if(!connectionAvailable)
            return;
        IUserService iUserService = ApiClient.getClient().create(IUserService.class);
        new ConnectService(context,OperationType.Delete).execute(iUserService.Delete(name));
    }

    @Override
    public void alertOkey() {

    }

    @Override
    public void alertCancel() {

    }

    @SuppressLint("StaticFieldLeak")
    private class ConnectService extends AsyncTask<Call,Void, Response<T>>{
        private ProgressDialog progressDialog;
        private Context context;
        private OperationType operationType;

        public ConnectService(Context context, OperationType operationType) {
            this.context = context;
            this.operationType = operationType;
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
                    userServiceListener.onSuccess(operationType, response);
                }else {
                    ToolAlertDialog
                            .newInstance(response.errorBody().string(),false,false)
                            .show(((FragmentActivity) context).getSupportFragmentManager(),UserService.class.getSimpleName());
                    userServiceListener.onFailure();
                }
            }catch (Exception e){
                ToolAlertDialog
                        .newInstance("Could not contact the service.\nPlease Try Again.",false,false)
                        .show(((FragmentActivity) context).getSupportFragmentManager(),UserService.class.getSimpleName());
                userServiceListener.onFailure();
            }
        }
    }

    public interface UserServiceListener<T>{
        void onSuccess(OperationType operationType,Response<T> response);
        void onFailure();
    }
}
