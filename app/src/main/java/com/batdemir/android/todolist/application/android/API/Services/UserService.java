package com.batdemir.android.todolist.application.android.API.Services;

import android.content.Context;

import com.batdemir.android.todolist.application.android.API.ApiClient;
import com.batdemir.android.todolist.application.android.API.IServices.IUserService;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.UserModel;

public class UserService extends ConnectService {

    private Context context;
    private IUserService iUserService;

    public UserService(Context context) {
        this.context = context;
        this.iUserService = ApiClient.getClient().create(IUserService.class);
    }

    public void Login(String userName, String userPassword) {
        connect(context, iUserService.Login(userName, userPassword), OperationType.UserLogin);
    }

    public void Insert(UserModel userModel) {
        connect(context, iUserService.Insert(userModel), OperationType.UserInsert);
    }

    public void Update(UserModel userModel) {
        connect(context, iUserService.Update(userModel), OperationType.UserUpdate);
    }

    public void Delete(UserModel userModel) {
        connect(context, iUserService.Delete(userModel), OperationType.UserDelete);
    }
}
