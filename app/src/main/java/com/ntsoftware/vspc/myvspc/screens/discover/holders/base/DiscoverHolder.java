package com.ntsoftware.vspc.myvspc.screens.discover.holders.base;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class DiscoverHolder extends RecyclerView.ViewHolder {

    protected View root;

    protected Context context;

    public DiscoverHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView;
        context = itemView.getContext();
    }

    public abstract void bind();
}
