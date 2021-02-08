package com.ntsoftware.vspc.myvspc.screens.discover.holders.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open abstract class DiscoverHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected val root: View = itemView

    protected val context: Context = itemView.context

    public abstract fun bind()
}