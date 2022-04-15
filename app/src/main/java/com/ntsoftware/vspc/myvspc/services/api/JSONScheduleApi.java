package com.ntsoftware.vspc.myvspc.services.api;

import com.ntsoftware.vspc.myvspc.screens.schedule.model.SchWeek;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.ScheduleDay;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.ScheduleWeek;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONScheduleApi {
    @GET("/schedule/week")
    Call<ScheduleWeek> getScheduleWeek(@Query("group") int group,
                                       @Query("subgroup") int subgroup,
                                       @Query("semester") int semester);

    @GET("/schedule/day")
    Call<ScheduleDay> getScheduleDay(@Query("group") int group,
                                     @Query("subgroup") int subgroup,
                                     @Query("semester") int semester,
                                     @Query("day") int day);
}
