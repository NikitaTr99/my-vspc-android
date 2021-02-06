package com.ntsoftware.vspc.myvspc.screens.discover;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntsoftware.vspc.myvspc.R;
import  com.ntsoftware.vspc.myvspc.screens.discover.holders.HolderType;
import com.ntsoftware.vspc.myvspc.screens.discover.holders.ScheduleHolder;
import com.ntsoftware.vspc.myvspc.screens.discover.holders.TodayHolder;
import com.ntsoftware.vspc.myvspc.screens.discover.holders.WeatherHolder;
import com.ntsoftware.vspc.myvspc.screens.discover.holders.base.DiscoverHolder;
import com.ntsoftware.vspc.myvspc.screens.discover.holders.base.EmtyHolder;

public class DiscoverAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public DiscoverAdapter() {
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HolderType.TODAY.ordinal()) {
            return HolderType.TODAY.ordinal();
        }
        else if (position == HolderType.WEATHER.ordinal()) {
            return HolderType.WEATHER.ordinal();
        }
        else if (position == HolderType.SCHEDULE.ordinal()) {
            return HolderType.SCHEDULE.ordinal();
        }
        else if (position == HolderType.SIMPLE_NEWS.ordinal()) {
            return HolderType.SIMPLE_NEWS.ordinal();
        }
        else return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;

        View holderLayoutView;

        if (viewType == HolderType.TODAY.ordinal()) {
            holderLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_discover_today,parent,false);
            viewHolder = new TodayHolder(holderLayoutView);
        }
        else if (viewType == HolderType.WEATHER.ordinal()) {
            holderLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_discover_weather,parent,false);
            viewHolder = new WeatherHolder(holderLayoutView);
        }
        else if (viewType == HolderType.SCHEDULE.ordinal()) {
            holderLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_discover_schedule,parent,false);
            viewHolder = new ScheduleHolder(holderLayoutView);
        }
//        else if (viewType == HolderType.SIMPLE_NEWS.ordinal()) {
//
//        }
        else {
            holderLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_discover_empty,parent,false);
            viewHolder = new EmtyHolder(holderLayoutView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DiscoverHolder)holder).bind();

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
