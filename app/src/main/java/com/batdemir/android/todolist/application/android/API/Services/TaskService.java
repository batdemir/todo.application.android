package com.batdemir.android.todolist.application.android.API.Services;

import android.content.Context;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.ITaskService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TaskModel;

public class TaskService extends ConnectService {

    private Context context;
    private ITaskService iTaskService;

    public TaskService(Context context) {
        this.context = context;
        this.iTaskService = ApiClient.getClient().create(ITaskService.class);
    }

    public void GetTasksByUser(String userName) {
        connect(context, iTaskService.GetTasksByUser(userName), OperationType.TaskGetTasksByUser);
    }

    public void Insert(TaskModel taskModel) {
        connect(context, iTaskService.Insert(taskModel), OperationType.TaskInsert);
    }

    public void Update(TaskModel taskModel) {
        connect(context, iTaskService.Update(taskModel), OperationType.TaskUpdate);
    }

    public void Delete(TaskModel taskModel) {
        connect(context, iTaskService.Delete(taskModel), OperationType.TaskDelete);
    }
}
