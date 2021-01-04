package com.ntsoftware.vspc.myvspc.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.services.NewsService;
import com.ntsoftware.vspc.myvspc.ui.home.news.NewsAdapter;
import com.ntsoftware.vspc.myvspc.ui.home.news.model.SimpleNews;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @BindView(R.id.home_news_rw)
    RecyclerView recyclerView;

    @BindView(R.id.rfl)
    SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);

        setHasOptionsMenu(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            loadNews();
            swipeRefreshLayout.setRefreshing(false);
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadNews();
    }

    private void loadNews() {
        NewsService.getInstance()
                .getJSONApi()
                .getAllSimpleNews()
                .enqueue(new Callback<List<SimpleNews>>() {
                    @Override
                    public void onResponse(Call<List<SimpleNews>> call, Response<List<SimpleNews>> response) {
                        recyclerView.setAdapter(new NewsAdapter(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<SimpleNews>> call, Throwable t) {
                        Log.e("NewsViewModel",t.getMessage());
                    }
                });
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
}