package com.ntsoftware.vspc.myvspc.screens.discover;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntsoftware.vspc.myvspc.MainActivity;
import com.ntsoftware.vspc.myvspc.R;

public class DiscoverFragment extends Fragment {

    ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        actionBar = ((MainActivity)getActivity()).getSupportActionBar();

        if(!actionBar.isShowing()) {
            actionBar.show();
        }
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }


}