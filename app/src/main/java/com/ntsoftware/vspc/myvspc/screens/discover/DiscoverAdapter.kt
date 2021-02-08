package com.ntsoftware.vspc.myvspc.screens.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ntsoftware.vspc.myvspc.R
import com.ntsoftware.vspc.myvspc.screens.discover.holders.HolderType
import com.ntsoftware.vspc.myvspc.screens.discover.holders.ScheduleHolder
import com.ntsoftware.vspc.myvspc.screens.discover.holders.TodayHolder
import com.ntsoftware.vspc.myvspc.screens.discover.holders.WeatherHolder
import com.ntsoftware.vspc.myvspc.screens.discover.holders.base.DiscoverHolder
import com.ntsoftware.vspc.myvspc.screens.discover.holders.base.EmptyHolder

class DiscoverAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        when(position) {
            HolderType.TODAY.ordinal -> return HolderType.TODAY.ordinal
            HolderType.WEATHER.ordinal -> return HolderType.WEATHER.ordinal
            HolderType.SCHEDULE.ordinal -> return HolderType.SCHEDULE.ordinal
            HolderType.SIMPLE_NEWS.ordinal -> return HolderType.SIMPLE_NEWS.ordinal
            else -> return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder? = null

        var holder_layout: View

        when(viewType){
            HolderType.TODAY.ordinal -> {
                holder_layout = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_discover_today,parent,false)
                holder = TodayHolder(holder_layout)
            }
            HolderType.WEATHER.ordinal -> {
                holder_layout = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_discover_weather,parent,false)
                holder = WeatherHolder(holder_layout)
            }
            HolderType.SCHEDULE.ordinal -> {
                holder_layout = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_discover_schedule,parent,false)
                holder = ScheduleHolder(holder_layout)
            }
//            HolderType.SIMPLE_NEWS.ordinal -> {
//
//            }
            else -> {
                holder_layout = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_discover_empty,parent,false)
                holder = EmptyHolder(holder_layout)
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DiscoverHolder).bind()
    }

    override fun getItemCount(): Int {
        return 4;
    }
}