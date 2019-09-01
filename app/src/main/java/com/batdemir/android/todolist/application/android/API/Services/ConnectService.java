package com.batdemir.android.todolist.application.android.API.Services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.FragmentActivity;

import com.batdemir.android.todolist.application.android.R;
import com.batdemir.android.todolist.application.android.Tools.AlertDialogTools.ToolAlertDialog;
import com.batdemir.android.todolist.application.android.Tools.ToolConnection;

import retrofit2.Call;
import retrofit2.Response;

public class ConnectService<T> implements
        ToolAlertDialog.AlertClickListener {

    public enum OperationType {
        Exception,

        CustomGet,
        CustomGetV2,

        PriorityGet,

        StatusGet,

        TaskGetTasksByUser,
        TaskInsert,
        TaskUpdate,
        TaskDelete,

        TodoListInsert,
        TodoListUpdate,
        TodoListDelete,

        TodoGetTodoByUser,
        TodoInsert,
        TodoUpdate,
        TodoDelete,

        UserLogin,
        UserInsert,
        UserUpdate,
        UserDelete;

        OperationType() {
        }
    }

    public void connect(Context context, Call call, OperationType operationType) {
        if (!ToolConnection.isConnected(context)) {
            ToolAlertDialog
                    .newInstance(context.getString(R.string.please_check_your_internet_connection), false, false)
                    .show(((FragmentActivity) context).getSupportFragmentManager(), CustomService.class.getSimpleName());
            return;
        }
        new AsyncConnectService(context, operationType).execute(call);
    }

    @SuppressLint("StaticFieldLeak")
    private class AsyncConnectService extends AsyncTask<Call, Void, Response<T>> {
        private ProgressDialog progressDialog;
        private Context context;
        private OperationType operationType;

        public AsyncConnectService(Context context, OperationType operationType) {
            this.context = context;
            this.operationType = operationType;
        }

        @Override
        protected void onPreExecute() {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage(context.getString(R.string.please_wait));
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
            ConnectServiceListener connectServiceListener = (ConnectServiceListener) activity;
            try {
                if (response.isSuccessful()) {
                    connectServiceListener.onSuccess(operationType, response);
                } else {
                    assert response.errorBody() != null;
                    ToolAlertDialog
                            .newInstance(response.errorBody().string(), false, false)
                            .show(((FragmentActivity) context).getSupportFragmentManager(), CustomService.class.getSimpleName());
                    connectServiceListener.onFailure(operationType);
                }
            } catch (Exception e) {
                ToolAlertDialog
                        .newInstance(context.getString(R.string.could_not_contact_the_service_please_try_again), false, false)
                        .show(((FragmentActivity) context).getSupportFragmentManager(), CustomService.class.getSimpleName());
                connectServiceListener.onFailure(OperationType.Exception);
            }
        }
    }

    public interface ConnectServiceListener<T> {
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
