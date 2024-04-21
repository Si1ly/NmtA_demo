package com.example.nmta.api;
import com.example.nmta.data.VideoItem.VideoItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {

    @GET("get/all")
    Call<List<VideoItem>> getAllVideo();

    @POST("post")
    Call<VideoItem> saveVideo(@Body VideoItem videoItem);
}
