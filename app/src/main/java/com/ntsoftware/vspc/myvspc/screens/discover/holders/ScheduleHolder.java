package com.ntsoftware.vspc.myvspc.screens.discover.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.screens.discover.holders.base.DiscoverHolder;
import com.ntsoftware.vspc.myvspc.screens.news.model.SimpleNews;
import com.ntsoftware.vspc.myvspc.screens.schedule.RvSchLessonAdapter;
import com.ntsoftware.vspc.myvspc.screens.schedule.model.SchWeek;
import com.ntsoftware.vspc.myvspc.services.ScheduleService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleHolder extends DiscoverHolder {

    protected static final HolderType type = HolderType.SCHEDULE;

    private RvSchLessonAdapter lessonAdapter;

    @BindView(R.id.rv_discover_schedule_lesson)
    RecyclerView recyclerView;

    @BindView(R.id.tv_service_message)
    TextView serviceMessage;

    public ScheduleHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
    }

    @Override
    public void bind() {
        loadASch();
    }

    private void loadASch() {

        serviceMessage.setText("Загрузка...");
        serviceMessage.setVisibility(View.VISIBLE);

        ScheduleService.getInstance()
                .getJSONApi()
                .getScheduleDay(1,2,4, 1)
                .enqueue(new Callback<SchWeek.SchDay>() {
                    @Override
                    public void onResponse(Call<SchWeek.SchDay> call, Response<SchWeek.SchDay> response) {
                        recyclerView.setAdapter(new RvSchLessonAdapter(response.body().getLessons()));
                        serviceMessage.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<SchWeek.SchDay> call, Throwable t) {
                        serviceMessage.setText("Сервис недоступен.");
                        serviceMessage.setVisibility(View.VISIBLE);
                    }
                });
    }
}
