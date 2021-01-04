package com.ntsoftware.vspc.myvspc.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ntsoftware.vspc.myvspc.services.NewsService;
import com.ntsoftware.vspc.myvspc.ui.home.news.NewsAdapter;
import com.ntsoftware.vspc.myvspc.ui.home.news.model.SimpleNews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    MutableLiveData<NewsAdapter> newsAdapterLiveData;

    public LiveData<NewsAdapter> getNewsAdapter() {
        if(newsAdapterLiveData == null){
            newsAdapterLiveData = new MutableLiveData<>();
            loadAdapter();
        }
        return newsAdapterLiveData;
    }

    private void loadAdapter(){

        List<SimpleNews> simpleNews;

        NewsService.getInstance()
                .getJSONApi()
                .getAllSimpleNews()
                .enqueue(new Callback<List<SimpleNews>>() {
                    @Override
                    public void onResponse(Call<List<SimpleNews>> call, Response<List<SimpleNews>> response) {
                        newsAdapterLiveData.postValue(new NewsAdapter(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<SimpleNews>> call, Throwable t) {
                        Log.e("NewsViewModel",t.getMessage());
                    }
                });
    }
}