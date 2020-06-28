package com.aryan.scool;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("users/login/user")
    Call<TokenResponse> login(@Field("uname") String username, @Field("pass") String password);

    @GET("users/profile")
    Call<UserModel> getUserProfile(@Header("Authorization") String token);
}
