package com.ntsoftware.vspc.myvspc.screens.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.SchWeek;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.ScheduleDay;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.ScheduleWeek;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvSchDaysAdapter extends RecyclerView.Adapter<RvSchDaysAdapter.SchDayViewHolder> {

    Context context;

    List<ScheduleDay> days;

    public RvSchDaysAdapter() {
        days = new ArrayList<>();
    }

    public RvSchDaysAdapter(Context context) {
        days = new ArrayList<>();
        this.context = context;
    }

    public RvSchDaysAdapter(Context context, ScheduleWeek week) {
        days = week.getDaysList();
        this.context = context;
    }

    public RvSchDaysAdapter(List<ScheduleDay> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public SchDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_schedule_day, parent,false);

        return new SchDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchDayViewHolder holder, int position) {

        ScheduleDay day = days.get(position);

        holder.name_of_day.setText(getNameOfDayByTag(day.getTag()));

        holder.lessons_recycler.setAdapter(new RvSchLessonAdapter(day.getLessons()));

    }

    public String getNameOfDayByTag(int tag) {
        switch (tag) {
            case 1: return "Понедельник";
            case 2: return "Вторник";
            case 3: return "Среда";
            case 4: return "Четверг";
            case 5: return "Пятница";
            case 6: return "Суббота";
            case 7: return "Воскресение";
            default: return "";
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public void replaceItems(Collection<ScheduleDay> days) {
        this.days.clear();
        this.days.addAll(days);
        this.notifyDataSetChanged();
    }

    public void replaceItems(ScheduleWeek week) {
        this.days.clear();
        this.days.addAll(week.getDays());
        this.notifyDataSetChanged();
    }

    public void clearItems() {
        days.clear();
        this.notifyDataSetChanged();
    }

    public class SchDayViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name_of_day)
        TextView name_of_day;

        @BindView(R.id.rv_schedule_lesson)
        RecyclerView lessons_recycler;

        public SchDayViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false);

            lessons_recycler.setLayoutManager(linearLayoutManager);
        }
    }
}
