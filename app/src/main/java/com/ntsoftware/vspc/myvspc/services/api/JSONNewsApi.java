package com.ntsoftware.vspc.myvspc.services.api;


import com.ntsoftware.vspc.myvspc.ui.home.news.model.NewsBlock;
import com.ntsoftware.vspc.myvspc.ui.home.news.model.SimpleNews;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONNewsApi {
    @GET("/news/all")
    Call<List<SimpleNews>> getAllSimpleNews();

    @GET("/news/blocks")
    Call<List<NewsBlock>> gelAllBlocksByNewsId(@Query("news_id") long id);
}
