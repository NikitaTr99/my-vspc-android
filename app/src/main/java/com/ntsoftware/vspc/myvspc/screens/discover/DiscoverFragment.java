package com.ntsoftware.vspc.myvspc.screens.discover;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntsoftware.vspc.myvspc.MainActivity;
import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.screens.news.RvNewsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverFragment extends Fragment {

    @BindView(R.id.discover_rv)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_discover, container, false);

        ButterKnife.bind(this,root);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);



        return  root;
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView.setAdapter(new DiscoverAdapter());
    }
}