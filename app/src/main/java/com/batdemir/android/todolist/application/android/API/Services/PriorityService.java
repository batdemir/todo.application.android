package com.batdemir.android.todolist.application.android.API.Services;

import android.content.Context;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.IPriorityService;

public class PriorityService extends ConnectService {

    private Context context;
    private IPriorityService iPriorityService;

    public PriorityService(Context context) {
        this.context = context;
        this.iPriorityService = ApiClient.getClient().create(IPriorityService.class);
    }

    public void GetPriority() {
        connect(context, iPriorityService.GetPriority(), OperationType.PriorityGet);
    }
}
