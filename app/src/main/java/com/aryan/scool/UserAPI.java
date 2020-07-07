package com.aryan.scool;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserAPI {

    @POST("users/login/user")
    Call<TokenResponse> login(@Body UserModel user);

    @GET("users/students")
    Call<List<UserModel>> getStudentList();

    @GET("users/students/{id}")
    Call<UserModel> getSelectedStudent(@Path("id") String _id);
}
