package com.batdemir.android.todolist.application.android.API.Services;

import android.content.Context;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.ICustomService;

public class CustomService extends ConnectService {

    private Context context;
    private ICustomService iCustomService;

    public CustomService(Context context) {
        this.context = context;
        this.iCustomService = ApiClient.getClient().create(ICustomService.class);
    }

    public void GetTodoTasksV2(String userName) {
        connect(context, iCustomService.GetTodoTasksV2(userName), OperationType.CustomGetV2);
    }
}
