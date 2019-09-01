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
import retrofit2.http.Query;

public interface ITaskService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("Task/GetTasksByUser")
    Call<ArrayList<TaskModel>> GetTasksByUser(
            @Query("userName") String userName
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("Task/Insert")
    Call<TaskModel> Insert(
            @Body TaskModel taskModel
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PUT("Task/Update")
    Call<TaskModel> Update(
            @Body TaskModel taskModel
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("Task/Delete")
    Call<TaskModel> Delete(
            @Body TaskModel taskModel
    );
}
