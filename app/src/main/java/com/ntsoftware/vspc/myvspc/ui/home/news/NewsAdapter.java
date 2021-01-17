package com.ntsoftware.vspc.myvspc.ui.home.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.ntsoftware.vspc.myvspc.R;
import com.ntsoftware.vspc.myvspc.ui.home.news.detail.NewsDetailActivity;
import com.ntsoftware.vspc.myvspc.ui.home.news.detail.NewsDetailFragment;
import com.ntsoftware.vspc.myvspc.ui.home.news.model.SimpleNews;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<SimpleNews> simpleNews;

    public NewsAdapter() {
        simpleNews = new ArrayList<>();
    }

    public NewsAdapter(List<SimpleNews> simpleNews) {
        this.simpleNews = simpleNews;
    }

    public void addItem(SimpleNews item) {
        simpleNews.add(item);
        this.notifyDataSetChanged();
    }

    public void  addItems(List<SimpleNews> items) {
        simpleNews.addAll(items);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout_id_from_list_item = R.layout.rv_item_newscard;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout_id_from_list_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(simpleNews.get(position));

        holder.parentLayout.setOnClickListener(
                v -> {
                    Bundle arguments = new Bundle();

                    arguments.putLong(NewsDetailActivity.ARG_NEWS_ID, holder.simpleNews.getId());
                    arguments.putString(NewsDetailActivity.ARG_NEWS_TITLE, holder.simpleNews.getTitle());
                    arguments.putString(NewsDetailActivity.ARG_NEWS_SUB_TITLE, holder.simpleNews.getSub_title());
                    arguments.putString(NewsDetailActivity.ARG_NEWS_CREATOR, holder.simpleNews.getCreator());
                    arguments.putLong(NewsDetailActivity.ARG_NEWS_TYPE, holder.simpleNews.getType());
                    arguments.putString(NewsDetailActivity.ARG_NEWS_IMAGE, holder.simpleNews.getImage());

                    Navigation.findNavController(holder.itemView).navigate(R.id.nav_news_detail_activity, arguments);
                }
        );
    }

    @Override
    public int getItemCount() {
        return simpleNews.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        public static final String AUTHOR_TIME_DIVIDEND = " • ";

        public SimpleNews simpleNews;

        @BindView(R.id.news_title)
        public TextView title;

        @BindView(R.id.news_sub_title)
        public TextView sub_title;

        @BindView(R.id.news_type_name)
        public TextView type_name;

        @BindView(R.id.news_author_time)
        public TextView author_time;

        @BindView(R.id.news_image)
        public ImageView image;

        @BindView(R.id.news_type_mark)
        public MaterialCardView typeMark;

        @BindView(R.id.news_card_layout)
        public MaterialCardView parentLayout;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(SimpleNews newsItem) {

            simpleNews = newsItem;

            title.setText(newsItem.getTitle() != null ? newsItem.getTitle() : "Null");

            sub_title.setText(newsItem.getSub_title());

            type_name.setText(newsItem.getType_name());

            String date = new SimpleDateFormat("dd.MM.yy HH:mm").format(newsItem.getDate());

            author_time.setText(newsItem.getCreator() + AUTHOR_TIME_DIVIDEND + date);


            Picasso.get()
                    .load(simpleNews.getImage())
                    .error(R.drawable.accent_grad)
                    .into(image);

            switch ((int) simpleNews.getType()) {
                case 1:
                    typeMark.setCardBackgroundColor(parentLayout.getContext().getColor(R.color.colorNewsTypeNull));
                    typeMark.setVisibility(View.GONE);
                    type_name.setVisibility(View.GONE);
                    break;
                case 2:
                    typeMark.setCardBackgroundColor(parentLayout.getContext().getColor(R.color.colorNewsTypeNews));
                    type_name.setText(R.string.news_type_name_news);
                    break;
                case 3:
                    typeMark.setCardBackgroundColor(parentLayout.getContext().getColor(R.color.colorNewsTypeNotification));
                    type_name.setText(R.string.news_type_name_notification);
                    break;
                case 4:
                    typeMark.setCardBackgroundColor(parentLayout.getContext().getColor(R.color.colorNewsTypeWarning));
                    type_name.setText(R.string.news_type_name_warning);
                    break;
            }
        }
    }
}
