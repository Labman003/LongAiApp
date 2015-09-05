package com.ouzhouren.longai.view.member.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.presenter.SearchPresenter;
import com.ouzhouren.longai.view.member.DetailFragmentAdapter;


public class DetailActivity extends AppCompatActivity {
    private MyLogger logger = MyLogger.benLog();
    private ViewPager viewPager;
  //  List<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        int position = getIntent().getIntExtra("position",0);
        DetailFragmentAdapter detailFragmentAdapter = new DetailFragmentAdapter(getSupportFragmentManager(), SearchPresenter.users);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(detailFragmentAdapter);
        viewPager.setCurrentItem(position);
    }


}
