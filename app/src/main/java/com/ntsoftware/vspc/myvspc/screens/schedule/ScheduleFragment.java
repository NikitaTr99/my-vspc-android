package com.ntsoftware.vspc.myvspc.screens.schedule;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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

import com.google.android.material.textview.MaterialTextView;
import com.ntsoftware.vspc.myvspc.MainActivity;
import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.services.ScheduleService;
import com.ntsoftware.vspc.myvspc.screens.news.model.SimpleNews;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.SchWeek;
import com.ntsoftware.vspc.myvspc.storage.ScheduleCache;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment {

    @BindView(R.id.rv_schedule)
    RecyclerView sch_recycler;

    SharedPreferences preferences;

    MaterialTextView status_view;

    ScheduleCache schedule_cache;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this,root);
        if(!((MainActivity)getActivity()).getSupportActionBar().isShowing()) {
            ((MainActivity)getActivity()).getSupportActionBar().show();
        }

        schedule_cache = new ScheduleCache(getContext());

        setHasOptionsMenu(true);
        sch_recycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        status_view = ((MainActivity)getActivity()).getStatus_text_view();

        return root;
    }

    private void loadAdapter(){
        status_view.setVisibility(View.VISIBLE);

        status_view.setText("Загрузка...");

        String group = preferences.getString("group",null);
        String subgroup = preferences.getString("subgroup",null);
        String semester = preferences.getString("semester",null);

        if (group != null && subgroup != null && semester != null) {
            if(schedule_cache.isScheduleSaved()) {
                loadFromCache();
            }
            else {
                loadFromService(group,subgroup,semester);
            }
        } else {
            status_view.setText("Для отображения расписания необходимо выбрать семестр, группу и подгруппу в настройках.");
        }
    }

    private void loadFromService(String group, String subgroup, String semester) {
        ScheduleService.getInstance()
                .getJSONApi()
                .getScheduleWeek(Integer.parseInt(group), Integer.parseInt(subgroup), Integer.parseInt(semester))
                .enqueue(new Callback<SchWeek>() {
                    @Override
                    public void onResponse(Call<SchWeek> call, Response<SchWeek> response) {
                        status_view.setVisibility(View.GONE);
                        sch_recycler.setAdapter(new RvSchDaysAdapter(response.body().getDays()));
                        schedule_cache.saveSchWeek(response.body().getDays());
                    }

                    @Override
                    public void onFailure(Call<SchWeek> call, Throwable t) {
                        status_view.setVisibility(View.VISIBLE);
                        status_view.setText("Ошибка соеденения с сервисом.");
                    }
                });
    }

    private void loadFromCache() {
        sch_recycler.setAdapter(new RvSchDaysAdapter(schedule_cache.getSchWeek()));
        status_view.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadAdapter();
    }

    @Override
    public void onStop() {
        super.onStop();
        status_view.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.schedule_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}