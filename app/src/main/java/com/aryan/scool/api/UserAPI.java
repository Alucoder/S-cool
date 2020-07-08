package com.aryan.scool.api;

import com.aryan.scool.TokenResponse;
import com.aryan.scool.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("users/login/user")
    Call<TokenResponse> login(@Body UserModel user);

    @GET("users/profile")
    Call<UserModel> getUserProfile(@Header("Authorization") String token);
}
