package com.ntsoftware.vspc.myvspc.screens.discover.holders

import android.view.TextureView
import android.view.View
import android.widget.TextView
import com.ntsoftware.vspc.myvspc.R
import com.ntsoftware.vspc.myvspc.screens.discover.holders.base.DiscoverHolder
import java.text.SimpleDateFormat
import java.util.*

class TodayHolder(itemView: View): DiscoverHolder(itemView) {

    private val info_text: TextView = itemView.findViewById(R.id.tv_home_info_date)

    override fun bind() {
        val locale = itemView.context.resources.configuration.locale
        info_text.text = SimpleDateFormat("EEEE, dd MMMM",locale).format(Date()).capitalize()
    }
}