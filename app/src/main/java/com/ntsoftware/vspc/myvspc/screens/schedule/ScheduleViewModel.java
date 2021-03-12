package com.ntsoftware.vspc.myvspc.screens.schedule;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;

import com.ntsoftware.vspc.myvspc.screens.schedule.model.SchWeek;
import com.ntsoftware.vspc.myvspc.services.ScheduleService;
import com.ntsoftware.vspc.myvspc.storage.ScheduleCache;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleViewModel extends AndroidViewModel {

    private MutableLiveData<List<SchWeek.SchDay>> schedule_days_live_data;

    ScheduleCache schedule_cache;

    public ScheduleViewModel(@NonNull Application application) {
        super(application);
        schedule_cache = new ScheduleCache(getApplication());
    }

    public LiveData<List<SchWeek.SchDay>> getSchDays() {

        if (schedule_days_live_data == null) {
            schedule_days_live_data = new MutableLiveData<>();
            loadSchedule();
        }

        return schedule_days_live_data;
    }

    public void updateSchedule() {
        loadSchedule();
    }


    private void loadSchedule() {

        if(schedule_cache.isScheduleSaved()) {
            schedule_days_live_data.postValue(schedule_cache.getSchWeek());
        }
        else {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());

            String group = preferences.getString("group",null);
            String subgroup = preferences.getString("subgroup",null);
            String semester = preferences.getString("semester",null);

            try {
                ScheduleService.getInstance()
                        .getJSONApi()
                        .getScheduleWeek(Integer.parseInt(group), Integer.parseInt(subgroup), Integer.parseInt(semester))
                        .enqueue(new Callback<SchWeek>() {
                            @Override
                            public void onResponse(Call<SchWeek> call, Response<SchWeek> response) {
                                schedule_days_live_data.postValue(response.body().getDays());
                                schedule_cache.saveSchWeek(response.body().getDays());
                                schedule_cache.setScheduleNeedReload(false);
                            }

                            @Override
                            public void onFailure(Call<SchWeek> call, Throwable t) {
                                Log.e("ScheduleViewModel", t.getMessage());
                            }
                        });
            } catch (Exception e) {
                if (group == null || subgroup == null || semester == null) {
                    Log.e("ScheduleViewModel", "Preferences is empty");
                    schedule_days_live_data.postValue(new ArrayList<>());
                }
            }
        }
    }

}
