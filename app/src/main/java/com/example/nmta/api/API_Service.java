package com.example.nmta.api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Service {

    private Retrofit retrofit;

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy ss-mm-hh").create();

    public API_Service() {
        initRetrofit();
    }

    public void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.108:8080/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }
}
