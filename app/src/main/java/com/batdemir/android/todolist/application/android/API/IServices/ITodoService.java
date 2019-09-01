package com.batdemir.android.todolist.application.android.API.IServices;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ITodoService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("Todo/GetTodoByUser")
    Call<ArrayList<TodoModel>> GetTodoByUser(
            @Query("userName") String userNamne
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("Todo/Insert")
    Call<TodoModel> Insert(
            @Body TodoModel todoModel
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PUT("Todo/Update")
    Call<TodoModel> Update(
            @Body TodoModel todoModel
    );

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("Todo/Delete")
    Call<TodoModel> Delete(
            @Body TodoModel todoModel
    );
}
