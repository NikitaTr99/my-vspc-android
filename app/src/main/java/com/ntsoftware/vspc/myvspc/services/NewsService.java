package com.ntsoftware.vspc.myvspc.services;



import com.ntsoftware.vspc.myvspc.services.api.JSONNewsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsService {
    private static NewsService mInstance;

    private static final String BASE_URL = "https://nikitatr99.fvds.ru";
//    private static final String BASE_URL = "http://192.168.43.97:8085";

    private Retrofit mRetrofit;

    private NewsService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public JSONNewsApi getJSONApi() {
        return mRetrofit.create(JSONNewsApi.class);
    }


    public static NewsService getInstance() {
        if (mInstance == null) {
            mInstance = new NewsService();
        }
        return mInstance;
    }

}
