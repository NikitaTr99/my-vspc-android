package com.ntsoftware.vspc.myvspc.services.api;

import com.ntsoftware.vspc.myvspc.screens.schedule.model.SchWeek;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONScheduleApi {
    @GET("/schedule/get")
    Call<SchWeek> getScheduleWeek(@Query("group") int group,
                                  @Query("subgroup") int subgroup,
                                  @Query("semester") int semester);
    @GET("/schedule/getd")
    Call<SchWeek.SchDay> getScheduleDay(@Query("group") int group,
                                  @Query("subgroup") int subgroup,
                                  @Query("semester") int semester,
                                  @Query("day") int day);
}
