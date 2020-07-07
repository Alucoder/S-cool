package com.aryan.scool;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AttendanceAPI {

    @POST("attendance")
    Call<Void> takeAttendance(@Header("Authorization")String token, @Body AttendanceModel attendance);

}
