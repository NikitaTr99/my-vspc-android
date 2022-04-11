package com.ntsoftware.vspc.myvspc.services;

import com.ntsoftware.vspc.myvspc.services.api.JSONNewsApi;
import com.ntsoftware.vspc.myvspc.services.api.JSONScheduleApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleService {
    private static ScheduleService mInstance;

    private static final String BASE_URL = "https://nikitatr99.fvds.ru";
//    private static final String BASE_URL = "http://192.168.43.97:8087";

    private Retrofit mRetrofit;

    private ScheduleService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public JSONScheduleApi getJSONApi() {
        return mRetrofit.create(JSONScheduleApi.class);
    }


    public static ScheduleService getInstance() {
        if (mInstance == null) {
            mInstance = new ScheduleService();
        }
        return mInstance;
    }
}
