package com.aryan.scool;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("users/login/user")
    Call<TokenResponse> login(@Body UserModel users);

    @GET("users/students")
    Call<List<UserModel>> getStudentList();
}
