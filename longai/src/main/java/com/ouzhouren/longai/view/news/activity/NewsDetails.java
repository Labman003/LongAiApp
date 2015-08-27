package com.ouzhouren.longai.view.news.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ouzhouren.longai.R;

/**
 * Created by 郭泽锋 on 2015/8/17.
 */
public class NewsDetails extends Activity {
    private TextView titleTV, authorTV;
    private Button news_comment;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        news_comment = (Button) findViewById(R.id.news_comment);
        news_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsDetails.this, NewsComment.class);
                startActivity(intent);
            }
        });
//        mToolbar = (Toolbar) findViewById(R.id.news_detail_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
//        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        mCollapsingToolbarLayout.setTitle("CollapsingToolbarLayout");
        //通过CollapsingToolbarLayout修改字体颜色
//        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
//        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色


//        titleTV = (TextView) findViewById(R.id.news_details_titleTV);
//        authorTV = (TextView) findViewById(R.id.news_details_authorTV);
//        int position = getIntent().getIntExtra("position", 0);
//        News newsItem = NewsFragment.newsLists.get(position);

//        titleTV.setText(newsItem.getTitle());
//        authorTV.setText(newsItem.getAuthor());
    }
}
