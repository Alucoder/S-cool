package com.aryan.scool.Interfaces;

import com.aryan.scool.Helper.TokenResponse;
import com.aryan.scool.Models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @GET("users/profile")
    Call<UserModel> getUserProfile(@Header("Authorization")String token);

    @GET("users/userteacher")
    Call<List<UserModel>> getTeacherList();


}
