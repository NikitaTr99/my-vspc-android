package com.ntsoftware.vspc.myvspc.screens.discover.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ntsoftware.vspc.myvspc.R
import com.ntsoftware.vspc.myvspc.screens.discover.holders.base.DiscoverHolder
import com.ntsoftware.vspc.myvspc.screens.schedule.RvSchLessonAdapter
import com.ntsoftware.vspc.myvspc.screens.schedule.model.SchWeek.SchDay
import com.ntsoftware.vspc.myvspc.services.ScheduleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleHolder(itemView: View): DiscoverHolder(itemView) {

    private val recycler_view: RecyclerView = itemView.findViewById(R.id.rv_discover_schedule_lesson)

    private val service_message: TextView = itemView.findViewById(R.id.tv_service_message)

    val type: HolderType = HolderType.SCHEDULE

    private var lesson_adapter: RvSchLessonAdapter = RvSchLessonAdapter()

    init {
        recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    override fun bind() {
        service_message.text = "Загрузка..."
        service_message.visibility = View.VISIBLE

        ScheduleService.getInstance()
                .jsonApi
                .getScheduleDay(1, 2, 4, 1)
                .enqueue(
                        object : Callback<SchDay> {
                            override fun onResponse(call: Call<SchDay>, response: Response<SchDay>) {
                                service_message.visibility = View.GONE
                                recycler_view.adapter = RvSchLessonAdapter(response.body()?.lessons)
                            }

                            override fun onFailure(call: Call<SchDay>, t: Throwable) {
                                service_message.visibility = View.VISIBLE
                                service_message.text = "Сервис недоступен."
                            }
                        }
                )
    }

}