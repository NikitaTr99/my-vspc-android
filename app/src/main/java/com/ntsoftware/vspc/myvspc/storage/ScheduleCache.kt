package com.ntsoftware.vspc.myvspc.storage

import android.content.Context
import com.google.gson.Gson
import com.ntsoftware.vspc.myvspc.screens.schedule.model.SchWeek

const val SHARPREF_NAME = "appdata"
const val SHARPREF_APPDATA_SCHEDULE = "appdata_schedule"
const val SHARPREF_APPDATA_SCHEDULE_NEED = "appdata_schedule_need_reload"

class ScheduleCache(val context: Context) {

    private val preferences by lazy {
        context.getSharedPreferences(SHARPREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveSchWeek(sch: List<SchWeek.SchDay>) {
        val editor = preferences.edit()
        editor.putString(SHARPREF_APPDATA_SCHEDULE, Gson().toJson(sch))
        editor.apply()
    }

    fun getSchWeek() : List<SchWeek.SchDay> {
        val json: String? = preferences.getString(SHARPREF_APPDATA_SCHEDULE, null)
        if(json != null) {
            return Gson().fromJson(json,Array<SchWeek.SchDay>::class.java).toList()
        }
        return ArrayList()
    }

    fun cleanSchWeek()  {
        val editor = preferences.edit()
        editor.remove(SHARPREF_APPDATA_SCHEDULE)
        editor.apply()
    }

    fun isScheduleSaved(): Boolean {
        return preferences.contains(SHARPREF_APPDATA_SCHEDULE)
    }

    fun setScheduleNeedReload(need: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(SHARPREF_APPDATA_SCHEDULE_NEED, need)
        editor.apply()
    }

    fun isScheduleNeedReload(): Boolean {
        if (preferences.contains(SHARPREF_APPDATA_SCHEDULE_NEED)){
            return preferences.getBoolean(SHARPREF_APPDATA_SCHEDULE_NEED, true)
        }
        else return false;
    }



}