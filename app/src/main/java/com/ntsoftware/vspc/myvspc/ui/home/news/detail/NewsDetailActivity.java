package com.ntsoftware.vspc.myvspc.ui.home.news.detail;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.ntsoftware.vspc.myvspc.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {

    public static final String ARG_NEWS_ID = "news_id";
    public static final String ARG_NEWS_TITLE = "news_title";
    public static final String ARG_NEWS_SUB_TITLE = "news_sub_title";
    public static final String ARG_NEWS_CREATOR = "news_creator";
    public static final String ARG_NEWS_TYPE = "news_type";
    public static final String ARG_NEWS_IMAGE = "news_image";



    @BindView(R.id.det_toolbar)
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_news_detail2);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false );
        }


        if(savedInstanceState == null){
            Bundle arguments = new Bundle();

            arguments.putLong(NewsDetailFragment.ARG_NEWS_ID, getIntent().getLongExtra(NewsDetailActivity.ARG_NEWS_ID,0));
            arguments.putString(ARG_NEWS_TITLE, getIntent().getStringExtra(ARG_NEWS_TITLE));
            arguments.putString(ARG_NEWS_SUB_TITLE, getIntent().getStringExtra(ARG_NEWS_SUB_TITLE));
            arguments.putString(ARG_NEWS_CREATOR, getIntent().getStringExtra(ARG_NEWS_CREATOR));
            arguments.putLong(ARG_NEWS_TYPE, getIntent().getLongExtra(ARG_NEWS_TYPE,0));
            arguments.putString(ARG_NEWS_IMAGE, getIntent().getStringExtra(ARG_NEWS_IMAGE));


            NewsDetailFragment newsDetailFragment = new NewsDetailFragment();

            newsDetailFragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction().add(R.id.news_detail_container,newsDetailFragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}