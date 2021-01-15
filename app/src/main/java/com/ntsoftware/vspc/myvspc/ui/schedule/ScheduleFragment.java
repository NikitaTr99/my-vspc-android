package com.ntsoftware.vspc.myvspc.ui.schedule;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.services.NewsService;
import com.ntsoftware.vspc.myvspc.services.ScheduleService;
import com.ntsoftware.vspc.myvspc.ui.home.news.NewsAdapter;
import com.ntsoftware.vspc.myvspc.ui.home.news.model.SimpleNews;
import com.ntsoftware.vspc.myvspc.ui.schedule.model.SchWeek;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment {

    @BindView(R.id.rv_schedule)
    RecyclerView sch_recycler;

    SchDaysAdapter days_adapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this,root);
        setHasOptionsMenu(true);

        days_adapter = new SchDaysAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);

        sch_recycler.setLayoutManager(linearLayoutManager);

        sch_recycler.setAdapter(days_adapter);

        loadAdapter();

        return root;
    }

    private void loadAdapter(){

        List<SimpleNews> simpleNews;

        ScheduleService.getInstance()
                .getJSONApi()
                .getSchedule(1,2,4)
                .enqueue(new Callback<SchWeek>() {
                    @Override
                    public void onResponse(Call<SchWeek> call, Response<SchWeek> response) {
                        days_adapter = new SchDaysAdapter(response.body().getDays());
                        sch_recycler.setAdapter(days_adapter);
                    }

                    @Override
                    public void onFailure(Call<SchWeek> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.schedule_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}