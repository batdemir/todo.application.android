package com.batdemir.android.todolist.application.android.API.IServices;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TaskModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ITaskService {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("ConstantTableTasks")
    Call<ArrayList<TaskModel>> Get();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("ConstantTableTasks/{id}")
    Call<TaskModel> GetByName(
            @Path("id") String name
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("ConstantTableTasks")
    Call<TaskModel> Insert(
            @Body TaskModel taskModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("ConstantTableTasks/{id}")
    Call<TaskModel> Update(
            @Path("id") String name,
            @Body TaskModel taskModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @DELETE("ConstantTableTasks/{id}")
    Call<TaskModel> Delete(
            @Path("id") String name
    );
}
