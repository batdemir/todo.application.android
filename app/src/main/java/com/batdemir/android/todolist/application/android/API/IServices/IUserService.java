package com.batdemir.android.todolist.application.android.API.IServices;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.UserModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUserService {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("ConstantTableUsers")
    Call<ArrayList<UserModel>> Get();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("ConstantTableUsers/{id}")
    Call<UserModel> GetByName(
            @Path("id") String name
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("ConstantTableUsers")
    Call<UserModel> Insert(
            @Body UserModel userModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("ConstantTableUsers/{id}")
    Call<UserModel> Update(
            @Path("id") String name,
            @Body UserModel userModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @DELETE("ConstantTableUsers/{id}")
    Call<UserModel> Delete(
            @Path("id") String name
    );

}
