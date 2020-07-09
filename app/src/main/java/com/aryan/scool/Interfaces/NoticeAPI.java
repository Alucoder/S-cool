package com.aryan.scool.Interfaces;

import com.aryan.scool.Models.NoticeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NoticeAPI {
    @GET("notice")
    Call<List<NoticeModel>> getNotice();

}
