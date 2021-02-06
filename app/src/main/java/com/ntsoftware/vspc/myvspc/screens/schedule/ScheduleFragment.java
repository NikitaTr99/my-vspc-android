package com.ntsoftware.vspc.myvspc.screens.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ntsoftware.vspc.myvspc.MainActivity;
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
    private ActionBar actionBar;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this,root);
        setHasOptionsMenu(true);

        actionBar = ((MainActivity)getActivity()).getSupportActionBar();

        if(!actionBar.isShowing()) {
            actionBar.show();
        }

        days_adapter = new RvSchDaysAdapter();

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
                        days_adapter = new RvSchDaysAdapter(response.body().getDays());
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