package com.aryan.scool;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUrl {

    public static final String BASE_URL = "http://10.0.2.2:30022/";

    public static String token = "Bearer ";
    public static String imagePath = BASE_URL + "uploads/" ;

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
