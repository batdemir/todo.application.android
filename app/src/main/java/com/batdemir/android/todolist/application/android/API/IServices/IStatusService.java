package com.batdemir.android.todolist.application.android.API.IServices;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.StatusModel;
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

public interface IStatusService {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("ConstantTableStatus")
    Call<ArrayList<StatusModel>> Get();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("ConstantTableStatus")
    Call<StatusModel> GetByName(
            @Path("id") String name
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("ConstantTableStatus")
    Call<StatusModel> Insert(
            @Body StatusModel taskModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("ConstantTableStatus")
    Call<StatusModel> Update(
            @Path("id") String name,
            @Body StatusModel taskModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @DELETE("ConstantTableStatus")
    Call<StatusModel> Delete(
            @Path("id") String name
    );
}
