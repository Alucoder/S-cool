package com.aryan.scool.Interfaces;

import com.aryan.scool.Models.SubjectModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface SubjectAPI {
    @GET("/subject/mysubject/{id}")
    Call<List<SubjectModel>> getMySubject(@Header("Authorization") String token, @Path("id") String _id);

}
