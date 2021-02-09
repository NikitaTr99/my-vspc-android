package com.ntsoftware.vspc.myvspc.screens.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ntsoftware.vspc.myvspc.MainActivity;
import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.services.NewsService;
import com.ntsoftware.vspc.myvspc.screens.news.model.SimpleNews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    @BindView(R.id.home_news_rw)
    RecyclerView recyclerView;

    @BindView(R.id.rfl)
    SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this,root);

        if(!((MainActivity)getActivity()).getSupportActionBar().isShowing()) {
            ((MainActivity)getActivity()).getSupportActionBar().show();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(new RvNewsAdapter());

        swipeRefreshLayout.setOnRefreshListener(() -> {
            loadNews();
        });

        loadNews();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void loadNews() {
        NewsService.getInstance()
                .getJSONApi()
                .getAllSimpleNews()
                .enqueue(new Callback<List<SimpleNews>>() {
                    @Override
                    public void onResponse(Call<List<SimpleNews>> call, Response<List<SimpleNews>> response) {
                        recyclerView.setAdapter(new RvNewsAdapter(response.body()));
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<SimpleNews>> call, Throwable t) {
                        Log.e("NewsViewModel",t.getMessage());
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }
}