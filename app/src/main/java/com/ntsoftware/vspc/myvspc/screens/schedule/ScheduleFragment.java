package com.ntsoftware.vspc.myvspc.screens.schedule;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.services.ScheduleService;
import com.ntsoftware.vspc.myvspc.screens.news.model.SimpleNews;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.SchWeek;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment {

    @BindView(R.id.rv_schedule)
    RecyclerView sch_recycler;

    RvSchDaysAdapter days_adapter;

    SharedPreferences preferences;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this,root);
        setHasOptionsMenu(true);

        days_adapter = new RvSchDaysAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);

        sch_recycler.setLayoutManager(linearLayoutManager);

        sch_recycler.setAdapter(days_adapter);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        loadAdapter();

        return root;
    }

    private void loadAdapter(){

        String group = preferences.getString("group",null);
        String subgroup = preferences.getString("subgroup",null);
        String semester = preferences.getString("semester",null);

        if (group != null && subgroup != null && semester != null) {
            ScheduleService.getInstance()
                    .getJSONApi()
                    .getScheduleWeek(Integer.parseInt(group), Integer.parseInt(subgroup), Integer.parseInt(semester))
                    .enqueue(new Callback<SchWeek>() {
                        @Override
                        public void onResponse(Call<SchWeek> call, Response<SchWeek> response) {
                            days_adapter = new RvSchDaysAdapter(response.body().getDays());
                            sch_recycler.setAdapter(days_adapter);
                        }

                        @Override
                        public void onFailure(Call<SchWeek> call, Throwable t) {

                        }
                    });
        }
        else {
            //TODO
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.schedule_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}