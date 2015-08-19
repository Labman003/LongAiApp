package com.ouzhouren.longai.module.news.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ouzhouren.longai.R;

/**
 * Created by 郭泽锋 on 2015/8/17.
 */
public class NewsDetails extends Activity {
    private TextView titleTV,authorTV;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

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
