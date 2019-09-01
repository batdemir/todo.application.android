package com.batdemir.android.todolist.application.android.API.IServices;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.PriorityModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface IPriorityService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("Priority/GetPriority")
    Call<ArrayList<PriorityModel>> GetPriority();
}
