package com.ntsoftware.vspc.myvspc.screens.discover.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ntsoftware.vspc.myvspc.screens.discover.holders.base.DiscoverHolder;
import com.ntsoftware.vspc.myvspc.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TodayHolder extends DiscoverHolder {


    private TextView info_text;

    public TodayHolder(@NonNull View itemView) {
        super(itemView);
        info_text = itemView.findViewById(R.id.tv_home_info_date);
    }

    public void bind() {
        Locale loc = itemView.getContext().getResources().getConfiguration().locale;
        info_text.setText(new SimpleDateFormat("EEEE, dd MMMM",loc).format(new Date()));
    }
}
