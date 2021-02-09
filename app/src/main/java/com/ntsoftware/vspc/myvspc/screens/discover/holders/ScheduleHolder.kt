package com.ntsoftware.vspc.myvspc.screens.discover.holders

import android.content.SharedPreferences
import android.util.Log
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
import java.util.*

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
        val day_of_week: Int = getNumOfDayWeek()

        if (group != null && subgroup != null && semester != null && day_of_week != -1) {

            service_message.text = "Загрузка..."

            ScheduleService.getInstance()
                    .jsonApi
                    .getScheduleDay(group.toInt(), subgroup.toInt(), semester.toInt(), day_of_week)
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
            service_message.text = "Для отображения расписания необходимо выбрать семестр, группу и подгруппу в настройках."
        }
    }

    fun getNumOfDayWeek(): Int {
        val locale = itemView.context.resources.configuration.locale
        val day_of_week: Int = Calendar.getInstance(locale).get(Calendar.DAY_OF_WEEK)
        when(day_of_week) {
            Calendar.MONDAY -> return 1
            Calendar.TUESDAY -> return 2
            Calendar.WEDNESDAY -> return 3
            Calendar.THURSDAY -> return 4
            Calendar.FRIDAY -> return 5
            Calendar.SATURDAY -> return 6
            Calendar.SUNDAY -> return 7
            else -> return -1
        }
    }

}