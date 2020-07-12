package com.aryan.scool.Interfaces;

import com.aryan.scool.Models.ResultModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ResultAPI {
    @POST("result")
    Call<Void> postResult(@Header("Authorization")String token, @Body ResultModel result);

}
