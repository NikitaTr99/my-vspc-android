package com.ntsoftware.vspc.myvspc.storage

import android.content.Context
import com.google.gson.Gson
import com.ntsoftware.vspc.myvspc.screens.schedule.model.SchWeek

const val SHARPREF_NAME = "appdata"
const val SHARPREF_APPDATA_SCHEDULE = "appdata_schedule"

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

    fun isScheduleSaved(): Boolean {
        return preferences.contains(SHARPREF_APPDATA_SCHEDULE)
    }





}