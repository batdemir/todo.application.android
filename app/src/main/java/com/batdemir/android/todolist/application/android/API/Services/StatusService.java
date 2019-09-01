package com.batdemir.android.todolist.application.android.API.Services;

import android.content.Context;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.IStatusService;

public class StatusService extends ConnectService {

    private Context context;
    private IStatusService iStatusService;

    public StatusService(Context context) {
        this.context = context;
        this.iStatusService = ApiClient.getClient().create(IStatusService.class);
    }

    public void Get() {
        connect(context, iStatusService.GetStatus(), OperationType.StatusGet);
    }
}
