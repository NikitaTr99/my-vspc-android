package com.ntsoftware.vspc.myvspc.screens.news.detail;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.ntsoftware.vspc.myvspc.R;

public class NewsDetailActivity extends AppCompatActivity {

    public static final String ARG_NEWS_ID = "news_id";
    public static final String ARG_NEWS_TITLE = "news_title";
    public static final String ARG_NEWS_SUB_TITLE = "news_sub_title";
    public static final String ARG_NEWS_CREATOR = "news_creator";
    public static final String ARG_NEWS_TYPE = "news_type";
    public static final String ARG_NEWS_IMAGE = "news_image";

    public static Bundle newBundle(String id, String title, String subtitle, String creator, long type, String image) {

        Bundle bundle = new Bundle();

        bundle.putString(ARG_NEWS_ID, id);
        bundle.putString(ARG_NEWS_TITLE, title);
        bundle.putString(ARG_NEWS_SUB_TITLE, subtitle);
        bundle.putString(ARG_NEWS_CREATOR, creator);
        bundle.putLong(ARG_NEWS_TYPE, type);
        bundle.putString(ARG_NEWS_IMAGE,image);

        return bundle;
    }

    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_detail2);

        toolbar = findViewById(R.id.det_toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false );
        }


        if(savedInstanceState == null){
            Bundle arguments = new Bundle();

            arguments.putString(NewsDetailFragment.ARG_NEWS_ID, getIntent().getStringExtra(NewsDetailActivity.ARG_NEWS_ID));
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