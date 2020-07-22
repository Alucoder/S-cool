package com.aryan.scool.Interfaces;

import com.aryan.scool.Models.ClassModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ClassroomAPI {

    @GET("/subject/myClasses/{id}")
    Call<List<ClassModel>> getMyClasses(@Header("Authorization") String token, @Path("id") String _id);

}
