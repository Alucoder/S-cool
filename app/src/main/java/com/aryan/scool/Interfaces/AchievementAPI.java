package com.aryan.scool.Interfaces;

import com.aryan.scool.Models.Achievements;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AchievementAPI {


    @GET("achievements")
    Call<List<Achievements>> getTotalAchievements(@Header("Authorization")String token);

    @GET("achievements/{id}")
    Call<Achievements> getMyAchievements(@Header("Authorization")String token, @Path("id") String id);

}
