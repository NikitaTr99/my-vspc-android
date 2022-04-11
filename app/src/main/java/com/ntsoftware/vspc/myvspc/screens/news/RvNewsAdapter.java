package com.ntsoftware.vspc.myvspc.screens.news;

import android.content.Context;
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
import com.ntsoftware.vspc.myvspc.screens.news.detail.NewsDetailActivity;
import com.ntsoftware.vspc.myvspc.screens.news.model.AssembledNewsPreview;
import com.ntsoftware.vspc.myvspc.screens.news.model.SimpleNews;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RvNewsAdapter extends RecyclerView.Adapter<RvNewsAdapter.NewsViewHolder> {

    private List<AssembledNewsPreview> newsPreview;

    public RvNewsAdapter() {
        newsPreview = new ArrayList<>();
    }

    public RvNewsAdapter(List<AssembledNewsPreview> simpleNews) {
        this.newsPreview = simpleNews;
    }

    public void addItem(AssembledNewsPreview item) {
        newsPreview.add(item);
        this.notifyDataSetChanged();
    }

    public void addItems(List<AssembledNewsPreview> items) {
        newsPreview.addAll(items);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layout_id_from_list_item = R.layout.rv_item_newscard_variant;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout_id_from_list_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.bind(newsPreview.get(position));

        holder.parentLayout.setOnClickListener(
                v -> {
                    AssembledNewsPreview newsPreview = holder.newsPreview;

                    Bundle arguments = NewsDetailActivity.newBundle(
                            newsPreview.getNewsId().toString(),
                            newsPreview.getTitle(),
                            newsPreview.getSubTitle(),
                            newsPreview.getCreator(),
                            newsPreview.getType(),
                            newsPreview.getImageId().toString()
                    );

                    Navigation.findNavController(holder.itemView).navigate(R.id.nav_news_detail_activity, arguments);
                }
        );
    }

    @Override
    public int getItemCount() {
        return newsPreview.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        public static final String AUTHOR_TIME_DIVIDEND = " • ";

        public AssembledNewsPreview newsPreview;

        public TextView title;

        public TextView sub_title;

        public TextView type_name;

        public TextView author_time;

        public ImageView image;

        public MaterialCardView typeMark;

        public View parentLayout;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            sub_title = itemView.findViewById(R.id.news_sub_title);
            type_name = itemView.findViewById(R.id.news_type_name);
            author_time = itemView.findViewById(R.id.news_author_time);
            image = itemView.findViewById(R.id.news_image);
            typeMark = itemView.findViewById(R.id.news_type_mark);
            parentLayout = itemView.findViewById(R.id.news_card_layout);
        }

        void bind(AssembledNewsPreview newsItem) {

            newsPreview = newsItem;

            title.setText(newsItem.getTitle() != null ? newsItem.getTitle() : "Null");

            sub_title.setText(newsItem.getSubTitle());

            type_name.setText(newsItem.getTypeName());

            String date = new SimpleDateFormat("dd.MM.yy HH:mm").format(newsItem.getCreatedAt());

            author_time.setText(newsItem.getCreator() + AUTHOR_TIME_DIVIDEND + date);


            Picasso.get()
                    .load("https://nikitatr99.fvds.ru/image/" + newsPreview.getImageId())
                    .error(R.drawable.accent_grad)
                    .into(image);

            switch ((int) newsPreview.getType()) {
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
