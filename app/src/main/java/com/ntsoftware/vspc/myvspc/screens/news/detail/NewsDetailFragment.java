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
import com.ntsoftware.vspc.myvspc.services.NewsService;
import com.ntsoftware.vspc.myvspc.screens.news.model.NewsBlock;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private long news_id;
    private String title;
    private String sub_title;
    private String author;
    private long type;
    private String image;

    @BindView(R.id.news_block_list)
    public RecyclerView recyclerView;

    @BindView(R.id.detail_title)
    public TextView title_tv;

    @BindView(R.id.detail_sub_title)
    public TextView subtitle_tv;

    @BindView(R.id.detail_type_mark)
    MaterialCardView typeMark;

    @BindView(R.id.detail_image)
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
            news_id = getArguments().getLong(ARG_NEWS_ID);
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
        ButterKnife.bind(this, view);

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
                .load(image)
                .error(R.drawable.accent_grad)
                .into(imageView);





        NewsService.getInstance()
                .getJSONApi()
                .gelAllBlocksByNewsId(news_id)
                .enqueue(new Callback<List<NewsBlock>>() {
                    @Override
                    public void onResponse(Call<List<NewsBlock>> call, Response<List<NewsBlock>> response) {
                        recyclerView.setAdapter(new RvBlocksAdapter(response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<NewsBlock>> call, Throwable t) {
                        Log.e("NewsViewModel",t.getMessage());
                    }
                });


        return view;
    }
}