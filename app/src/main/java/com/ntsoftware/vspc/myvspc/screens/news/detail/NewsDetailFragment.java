package com.ntsoftware.vspc.myvspc.screens.news.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.screens.news.model.BlockEntity;
import com.ntsoftware.vspc.myvspc.services.NewsService;
import com.ntsoftware.vspc.myvspc.screens.news.model.NewsBlock;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailFragment extends Fragment {

    public static final String ARG_NEWS_ID = "news_id";
    public static final String ARG_NEWS_TITLE = "news_title";
    public static final String ARG_NEWS_SUB_TITLE = "news_sub_title";
    public static final String ARG_NEWS_CREATOR = "news_creator";
    public static final String ARG_NEWS_TYPE = "news_type";
    public static final String ARG_NEWS_IMAGE = "news_image";

    private String news_id;
    private String title;
    private String sub_title;
    private String author;
    private long type;
    private String image;

    public RecyclerView recyclerView;
    public TextView title_tv;
    public TextView subtitle_tv;
    MaterialCardView typeMark;
    ImageView imageView;

    public NewsDetailFragment() {
    }

    public static NewsDetailFragment newInstance(String param1, String param2) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news_id = getArguments().getString(ARG_NEWS_ID);
            title = getArguments().getString(ARG_NEWS_TITLE);
            sub_title = getArguments().getString(ARG_NEWS_SUB_TITLE);
            author = getArguments().getString(ARG_NEWS_CREATOR);
            type = getArguments().getLong(ARG_NEWS_TYPE,0);
            image = getArguments().getString(ARG_NEWS_IMAGE);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);
        recyclerView = view.findViewById(R.id.news_block_list);

        title_tv = view.findViewById(R.id.detail_title);
        subtitle_tv = view.findViewById(R.id.detail_sub_title);
        typeMark = view.findViewById(R.id.detail_type_mark);
        imageView = view.findViewById(R.id.detail_image);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        title_tv.setText(title);
        subtitle_tv.setText(sub_title);

        switch ((int) type) {
            case 1:
                typeMark.setCardBackgroundColor(getContext().getColor(R.color.colorNewsTypeNull));
                typeMark.setVisibility(View.GONE);
                break;
            case 2:
                typeMark.setCardBackgroundColor(getContext().getColor(R.color.colorNewsTypeNews));
                break;
            case 3:
                typeMark.setCardBackgroundColor(getContext().getColor(R.color.colorNewsTypeNotification));
                break;
            case 4:
                typeMark.setCardBackgroundColor(getContext().getColor(R.color.colorNewsTypeWarning));
                break;
        }

        Picasso.get()
                .load("https://nikitatr99.fvds.ru/image/"+ image)
                .error(R.drawable.accent_grad)
                .into(imageView);


        NewsService.getInstance()
                .getJSONApi()
                .gelAllBlocksByNewsId(news_id)
                .enqueue(new Callback<List<BlockEntity>>() {
                    @Override
                    public void onResponse(Call<List<BlockEntity>> call, Response<List<BlockEntity>> response) {
                        recyclerView.setAdapter(new RvBlocksAdapter(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<BlockEntity>> call, Throwable t) {
                        Log.e("NewsViewModel",t.getMessage());
                    }
                });


        return view;
    }
}