package com.batdemir.android.todolist.application.android.API.Services;

import android.content.Context;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.ITodoListsService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoListModel;

public class TodoListService extends ConnectService {

    private Context context;
    private ITodoListsService iTodoListsService;

    public TodoListService(Context context) {
        this.context = context;
        this.iTodoListsService = ApiClient.getClient().create(ITodoListsService.class);
    }

    public void Insert(TodoListModel todoListModel) {
        connect(context, iTodoListsService.Insert(todoListModel), OperationType.TodoListInsert);
    }

    public void Update(TodoListModel todoListModel) {
        connect(context, iTodoListsService.Update(todoListModel), OperationType.TodoListUpdate);
    }

    public void Delete(TodoListModel todoListModel) {
        connect(context, iTodoListsService.Delete(todoListModel), OperationType.TodoListDelete);
    }
}
