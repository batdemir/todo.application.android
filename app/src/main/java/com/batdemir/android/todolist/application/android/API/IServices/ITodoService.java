package com.batdemir.android.todolist.application.android.API.IServices;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ITodoService {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("Todo/Insert")
    Call<TodoModel> Insert(
            @Body TodoModel taskModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("Todo/Update")
    Call<TodoModel> Update(
            @Body TodoModel taskModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @DELETE("Todo/Delete")
    Call<TodoModel> Delete(
            @Body TodoModel taskModel
    );
}
