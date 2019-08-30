package com.batdemir.android.todolist.application.android.API.Services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.FragmentActivity;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.ITodoListsService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoListModel;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.ToolConnection;

import retrofit2.Call;
import retrofit2.Response;

public class TodoListService<T> implements
        ToolAlertDialog.AlertClickListener {

    private Context context;
    private boolean connectionAvailable = false;

    public enum OperationType{
        Exception,
        Insert,
        Update,
        Delete;

        OperationType() {
        }
    }

    public TodoListService(Context context) {
        this.context = context;
        connectionAvailable = ToolConnection.isConnected(context);
        if (!connectionAvailable)
            ToolAlertDialog
                    .newInstance("Please Check Your Internet Connection.", false, false)
                    .show(((FragmentActivity) context).getSupportFragmentManager(), UserService.class.getSimpleName());
    }

    public void Insert(TodoListModel todoListModel) {
        if (!connectionAvailable)
            return;
        ITodoListsService iTodoListsService = ApiClient.getClient().create(ITodoListsService.class);
        new ConnectService(context,OperationType.Insert).execute(iTodoListsService.Insert(todoListModel));
    }

    public void Update(TodoListModel todoListModel) {
        if (!connectionAvailable)
            return;
        ITodoListsService iTodoListsService = ApiClient.getClient().create(ITodoListsService.class);
        new ConnectService(context,OperationType.Update).execute(iTodoListsService.Update(todoListModel));
    }

    public void Delete(TodoListModel todoListModel) {
        if (!connectionAvailable)
            return;
        ITodoListsService iTodoListsService = ApiClient.getClient().create(ITodoListsService.class);
        new ConnectService(context,OperationType.Delete).execute(iTodoListsService.Delete(todoListModel));
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
            TodoListServiceListener todoListServiceListener = (TodoListServiceListener) activity;
            try {
                if (response.isSuccessful()) {
                    todoListServiceListener.onSuccess(operationType,response);
                } else {
                    ToolAlertDialog
                            .newInstance(response.errorBody().string(), false, false)
                            .show(((FragmentActivity) context).getSupportFragmentManager(), UserService.class.getSimpleName());
                    todoListServiceListener.onFailure(operationType);
                }
            } catch (Exception e) {
                ToolAlertDialog
                        .newInstance("Could not contact the service.\nPlease Try Again.", false, false)
                        .show(((FragmentActivity) context).getSupportFragmentManager(), UserService.class.getSimpleName());
                todoListServiceListener.onFailure(OperationType.Exception);
            }
        }
    }

    public interface TodoListServiceListener<T> {
        void onSuccess(OperationType operationType, Response<T> response);
        void onFailure(OperationType operationType);
    }

    @Override
    public void alertOkey() {

    }

    @Override
    public void alertCancel() {

    }
}
