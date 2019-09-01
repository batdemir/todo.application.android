package com.batdemir.android.todolist.application.android.API.Services;

import android.content.Context;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.ITodoService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoModel;

public class TodoService extends ConnectService {

    private Context context;
    private ITodoService iTodoService;

    public TodoService(Context context) {
        this.context = context;
        this.iTodoService = ApiClient.getClient().create(ITodoService.class);

    }

    public void GetTodoByUser(String userName){
        connect(context,iTodoService.GetTodoByUser(userName),OperationType.TodoGetTodoByUser);
    }

    public void Insert(TodoModel todoModel) {
        connect(context, iTodoService.Insert(todoModel), OperationType.TodoInsert);
    }

    public void Update(TodoModel todoModel) {
        connect(context, iTodoService.Update(todoModel), OperationType.TodoUpdate);
    }

    public void Delete(TodoModel todoModel) {
        connect(context, iTodoService.Delete(todoModel), OperationType.TodoDelete);
    }
}
