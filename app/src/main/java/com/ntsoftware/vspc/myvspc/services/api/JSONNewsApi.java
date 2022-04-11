package com.ntsoftware.vspc.myvspc.services.api;


import com.ntsoftware.vspc.myvspc.screens.news.model.AssembledNewsPreview;
import com.ntsoftware.vspc.myvspc.screens.news.model.BlockEntity;
import com.ntsoftware.vspc.myvspc.screens.news.model.NewsBlock;
import com.ntsoftware.vspc.myvspc.screens.news.model.SimpleNews;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONNewsApi {
    @GET("/news")
    Call<List<AssembledNewsPreview>> getAllSimpleNews();

    @GET("/news/{newsid}/blocks")
    Call<List<BlockEntity>> gelAllBlocksByNewsId(@Path("newsid") String id);
}
