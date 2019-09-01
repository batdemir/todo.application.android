package com.batdemir.android.todolist.application.android.API.IServices;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.CustomTodoTasksModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ICustomService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("Custom/GetTodoTasksV2")
    Call<ArrayList<CustomTodoTasksModel>> GetTodoTasksV2(
            @Query("userName") String userName
    );
}
