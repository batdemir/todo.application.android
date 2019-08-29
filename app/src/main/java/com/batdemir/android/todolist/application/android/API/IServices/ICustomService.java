package com.batdemir.android.todolist.application.android.API.IServices;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.CustomTodoModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ICustomService {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("Custom/GetTodoTasks")
    Call<ArrayList<CustomTodoModel>> GetTodoTasks(
            @Query("userName") String userName
    );
}
