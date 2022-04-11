package com.ntsoftware.vspc.myvspc.screens.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.LessonDetail;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.ScheduleDay;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.ScheduleLesson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RvSchLessonAdapter extends RecyclerView.Adapter<RvSchLessonAdapter.SchLessonViewHolder> {

    Context context;

    List<ScheduleLesson> lessons;

    public RvSchLessonAdapter() {
        lessons = new ArrayList<>();
    }

    public RvSchLessonAdapter(Collection<ScheduleLesson> lessons) {
        this.lessons = (List<ScheduleLesson>) lessons;
    }

    @NonNull
    @Override
    public SchLessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_schedule_lesson, parent, false);
        return new SchLessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchLessonViewHolder holder, int position) {

        ScheduleLesson lesson = lessons.get(position);

        holder.lesson_name.setText(lesson.getName());

        holder.lesson_detail.setText(lesson.getType() + ", " + lesson.getTeacher() + ", " + lesson.getAudience());

        holder.lesson_start.setText(lesson.getStart());

        holder.lesson_end.setText(lesson.getEnd());

        holder.lesson_break.setText(lesson.get_break() + "мин");
        holder.lesson_break.setVisibility(View.GONE);

    }

    public void addItems(List<ScheduleLesson> lessons) {
        this.lessons.addAll(lessons);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public class SchLessonViewHolder extends RecyclerView.ViewHolder {

        TextView lesson_name;
        TextView lesson_detail;
        TextView lesson_start;
        TextView lesson_end;
        TextView lesson_break;

        public SchLessonViewHolder(@NonNull View itemView) {
            super(itemView);
            lesson_name = itemView.findViewById(R.id.sch_lesson_name);
            lesson_detail = itemView.findViewById(R.id.sch_lesson_details);
            lesson_start = itemView.findViewById(R.id.sch_lesson_start);
            lesson_end = itemView.findViewById(R.id.sch_lesson_end);
            lesson_break = itemView.findViewById(R.id.sch_lesson_break);
        }
    }
}
