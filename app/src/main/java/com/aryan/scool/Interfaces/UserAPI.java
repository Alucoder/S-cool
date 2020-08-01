package com.aryan.scool.Interfaces;

import com.aryan.scool.Helper.TokenResponse;
import com.aryan.scool.Models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPI {

    @POST("users/login/user")
    Call<TokenResponse> login(@Body UserModel user);

    @GET("users/students")
    Call<List<UserModel>> getStudentList();

    @GET("users/students/{id}")
    Call<UserModel> getSelectedStudent(@Header("Authorization")String token, @Path("id") String _id);

    @GET("users/profile")
    Call<UserModel> getUserProfile(@Header("Authorization")String token);

    @GET("users/userteacher")
    Call<List<UserModel>> getTeacherList();

    @GET("users/students/class/{class}")
    Call<List<UserModel>> getSelectedClassStudent(@Header("Authorization")String token, @Path("class") String _id);

    @PUT("users/achievement/{id}")
    Call<UserModel> updateAchievements(@Header("Authorization")String token, @Path("id") String id, @Body UserModel users);


    @PUT("users/profile")
    Call<UserModel> updateProfile(@Header("Authorization")String token, @Body UserModel users);

    @FormUrlEncoded
    @PUT("users/changePassword")
    Call<Void> changePassword(@Header("Authorization")String token, @Field("password") String password);

    @FormUrlEncoded
    @POST("users/checkPassword/{id}")
    Call<Void> checkPassword(@Header("Authorization")String token, @Path("id") String id,  @Field("password") String password);
}
