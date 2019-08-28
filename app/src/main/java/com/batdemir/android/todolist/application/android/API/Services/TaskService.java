package com.batdemir.android.todolist.application.android.API.Services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.FragmentActivity;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.ITaskService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TaskModel;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.ToolConnection;

import retrofit2.Call;
import retrofit2.Response;

public class TaskService<T> implements
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

    public TaskService(Context context) {
        this.context = context;
        connectionAvailable = ToolConnection.isConnected(context);
        if (!connectionAvailable)
            ToolAlertDialog
                    .newInstance("Please Check Your Internet Connection.", false, false)
                    .show(((FragmentActivity) context).getSupportFragmentManager(), UserService.class.getSimpleName());
    }

    public void Get() {
        if (!connectionAvailable)
            return;
        ITaskService iTaskService = ApiClient.getClient().create(ITaskService.class);
        new ConnectService(context,OperationType.Get).execute(iTaskService.Get());
    }

    public void GetByName(String name) {
        if (!connectionAvailable)
            return;
        ITaskService iTaskService = ApiClient.getClient().create(ITaskService.class);
        new ConnectService(context,OperationType.GetByName).execute(iTaskService.GetByName(name));
    }

    public void Insert(TaskModel taskModel) {
        if (!connectionAvailable)
            return;
        ITaskService iTaskService = ApiClient.getClient().create(ITaskService.class);
        new ConnectService(context,OperationType.Insert).execute(iTaskService.Insert(taskModel));
    }

    public void Update(String name, TaskModel taskModel) {
        if (!connectionAvailable)
            return;
        ITaskService iTaskService = ApiClient.getClient().create(ITaskService.class);
        new ConnectService(context,OperationType.Update).execute(iTaskService.Update(name, taskModel));
    }

    public void Delete(String name) {
        if (!connectionAvailable)
            return;
        ITaskService iTaskService = ApiClient.getClient().create(ITaskService.class);
        new ConnectService(context,OperationType.Delete).execute(iTaskService.Delete(name));
    }

    @Override
    public void alertOkey() {

    }

    @Override
    public void alertCancel() {

    }

    @SuppressLint("StaticFieldLeak")
    private class ConnectService extends AsyncTask<Call, Void, Response<T>> {
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
            try {
                return calls[0].execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Response<T> response) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Activity activity = (Activity) context;
            UserServiceListener userServiceListener = (UserServiceListener) activity;
            try {
                if (response.isSuccessful()) {
                    userServiceListener.onSuccess(operationType,response);
                } else {
                    ToolAlertDialog
                            .newInstance(response.errorBody().string(), false, false)
                            .show(((FragmentActivity) context).getSupportFragmentManager(), UserService.class.getSimpleName());
                    userServiceListener.onFailure();
                }
            } catch (Exception e) {
                ToolAlertDialog
                        .newInstance("Could not contact the service.\nPlease Try Again.", false, false)
                        .show(((FragmentActivity) context).getSupportFragmentManager(), UserService.class.getSimpleName());
                userServiceListener.onFailure();
            }
        }
    }

    public interface UserServiceListener<T> {
        void onSuccess(OperationType operationType, Response<T> response);
        void onFailure();
    }
}
