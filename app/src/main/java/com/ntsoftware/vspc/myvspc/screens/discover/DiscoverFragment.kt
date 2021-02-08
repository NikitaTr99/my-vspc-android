package com.ntsoftware.vspc.myvspc.screens.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ntsoftware.vspc.myvspc.R

class DiscoverFragment: Fragment() {

    private lateinit var recycler_view: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = inflater.inflate(R.layout.fragment_discover, container, false)

        recycler_view = root.findViewById(R.id.discover_rv)

        recycler_view.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)

        recycler_view.adapter = DiscoverAdapter()

        return root
    }

}