package com.ntsoftware.vspc.myvspc.screens.discover.holders

import android.content.SharedPreferences
import android.view.View
import android.widget.TextView
import androidx.preference.Preference
import androidx.preference.PreferenceManager
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

    private var preference: SharedPreferences

    init {
        recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        preference = PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun bind() {
        service_message.visibility = View.VISIBLE

        val group: String? = preference.getString("group", null)
        val subgroup: String? = preference.getString("subgroup", null)
        val semester: String? = preference.getString("semester", null)

        if (group != null && subgroup != null && semester != null) {

            service_message.text = "Загрузка..."

            ScheduleService.getInstance()
                    .jsonApi
                    .getScheduleDay(group.toInt(), subgroup.toInt(), semester.toInt(), 1/*TODO*/)
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
        else {
            service_message.text = "Перейдите в настройки и выберете группу."
        }



    }

}