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
import retrofit2.http.Query;

public interface IUserService {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("User/Login")
    Call<UserModel> Login(
            @Query("userName") String userName,
            @Query("userPassword") String userPassword
    );


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("User/Insert")
    Call<UserModel> Insert(
            @Body UserModel taskModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @PUT("User/Update")
    Call<UserModel> Update(
            @Body UserModel taskModel
    );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("User/Delete")
    Call<UserModel> Delete(
            @Body UserModel taskModel
    );

}
