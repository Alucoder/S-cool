package com.aryan.scool.api;

import com.aryan.scool.model.AttendanceModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AttendanceAPI {

    @POST("attendance")
    Call<Void> takeAttendance(@Body AttendanceModel attendance);

}
