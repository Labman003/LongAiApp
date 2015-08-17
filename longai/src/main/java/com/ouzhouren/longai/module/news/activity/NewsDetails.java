package com.ouzhouren.longai.module.news.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.entity.News;
import com.ouzhouren.longai.module.news.NewsFragment;

/**
 * Created by 郭泽锋 on 2015/8/17.
 */
public class NewsDetails extends Activity {
    private TextView titleTV,authorTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        titleTV = (TextView) findViewById(R.id.news_details_titleTV);
        authorTV = (TextView) findViewById(R.id.news_details_authorTV);
        int position = getIntent().getIntExtra("position", 0);
        News newsItem = NewsFragment.newsLists.get(position);

        titleTV.setText(newsItem.getTitle());
        authorTV.setText(newsItem.getAuthor());
    }
}
