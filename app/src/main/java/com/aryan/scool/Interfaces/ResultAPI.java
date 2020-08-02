package com.aryan.scool.Interfaces;

import com.aryan.scool.Models.ResultModel;
import com.aryan.scool.Models.ResultViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ResultAPI {
    @POST("result")
    Call<Void> postResult(@Header("Authorization")String token, @Body ResultModel result);

    @GET("result/student/{id}")
    Call<List<ResultViewModel>> getResult(@Header("Authorization")String token, @Path("id") String _id);
}
