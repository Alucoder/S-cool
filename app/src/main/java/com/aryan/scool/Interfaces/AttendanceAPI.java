package com.aryan.scool.Interfaces;

import com.aryan.scool.Models.AttendanceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AttendanceAPI {

    @POST("attendance")
    Call<Void> takeAttendance(@Header("Authorization")String token, @Body AttendanceModel attendance);

    @GET("attendance/{uid}/total")
    Call<List<AttendanceModel>> getTotalAttendance(@Header("Authorization")String token, @Path("uid") String uid);

    @GET("attendance/{uid}/present")
    Call<List<AttendanceModel>> getPresentAttendance(@Header("Authorization")String token, @Path("uid") String uid);

}
