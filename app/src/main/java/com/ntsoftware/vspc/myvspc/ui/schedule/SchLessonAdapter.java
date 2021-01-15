package com.ntsoftware.vspc.myvspc.ui.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.ui.schedule.model.LessonDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchLessonAdapter extends RecyclerView.Adapter<SchLessonAdapter.SchLessonViewHolder> {

    Context context;

    List<LessonDetail> lessons;

    public SchLessonAdapter() {
    }

    public SchLessonAdapter(List<LessonDetail> lessons) {
        this.lessons = lessons;
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

        LessonDetail lessonDetail = lessons.get(position);

        holder.lesson_name.setText(lessonDetail.getName());

        holder.lesson_detail.setText(lessonDetail.getType() + ", " + lessonDetail.getTeacher() + ", " + lessonDetail.getAudience());

        holder.lesson_start.setText(lessonDetail.getStart());

        holder.lesson_end.setText(lessonDetail.getEnd());

        holder.lesson_break.setText(lessonDetail.get_break() + "мин");
        holder.lesson_break.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public class SchLessonViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sch_lesson_name)
        TextView lesson_name;

        @BindView(R.id.sch_lesson_details)
        TextView lesson_detail;

        @BindView(R.id.sch_lesson_start)
        TextView lesson_start;

        @BindView(R.id.sch_lesson_end)
        TextView lesson_end;

        @BindView(R.id.sch_lesson_break)
        TextView lesson_break;

        public SchLessonViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
