package com.batdemir.android.todolist.application.android.API.IServices;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.StatusModel;
import com.batdemir.android.todolist.application.android.Entity.ServiceModels.TodoListModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ITodoListsService {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("TableTodoLists")
    Call<ArrayList<TodoListModel>> Get();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("TableTodoLists")
    Call<TodoListModel> GetByName(
            @Path("id") String name
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("TableTodoLists")
    Call<TodoListModel> Insert(
            @Body TodoListModel taskModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("TableTodoLists")
    Call<TodoListModel> Update(
            @Path("id") String name,
            @Body TodoListModel taskModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @DELETE("TableTodoLists")
    Call<TodoListModel> Delete(
            @Path("id") String name
    );
}
