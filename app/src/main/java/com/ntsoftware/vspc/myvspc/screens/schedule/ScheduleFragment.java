package com.ntsoftware.vspc.myvspc.screens.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.ntsoftware.vspc.myvspc.MainActivity;
import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.storage.ScheduleCache;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleFragment extends Fragment {

    @BindView(R.id.rv_schedule)
    RecyclerView sch_recycler;

    MaterialTextView status_view;

    RvSchDaysAdapter days_adapter;

    ScheduleViewModel model;

    ScheduleCache schedule_cache;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        model = new ViewModelProvider(getActivity()).get(ScheduleViewModel.class);

        schedule_cache = new ScheduleCache(getContext());

        ButterKnife.bind(this,root);

        if(!((MainActivity)getActivity()).getSupportActionBar().isShowing()) {

            ((MainActivity)getActivity()).getSupportActionBar().show();

        }

        init();

        model.getSchDays().observe(getViewLifecycleOwner(), days -> days_adapter.replaceItems(days));

        if (schedule_cache.isScheduleNeedReload()) {
            schedule_cache.cleanSchWeek();
            model.updateSchedule();
        }

        return root;
    }

    private void init() {
        setHasOptionsMenu(true);

        sch_recycler.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));

        sch_recycler.setItemViewCacheSize(7);

        days_adapter = new RvSchDaysAdapter(getContext());

        sch_recycler.setAdapter(days_adapter);

        status_view = ((MainActivity)getActivity()).getStatus_text_view();

    }

    @Override
    public void onStop() {
        super.onStop();
        status_view.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (model.getSchDays().hasActiveObservers()) {
            model.getSchDays().removeObservers(getActivity());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.schedule_menu_action_update:
                schedule_cache.cleanSchWeek();
                model.updateSchedule();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.schedule_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}