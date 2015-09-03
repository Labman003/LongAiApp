package com.ouzhouren.longai.view.member.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.TextView;

import com.ouzhouren.longai.R;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.model.User;
import com.ouzhouren.longai.presenter.SearchPresenter;
import com.ouzhouren.longai.view.member.DetailFragmentAdapter;

import java.util.List;


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

    public void setUITextAndColor(Palette palette, User selectedUser) {
        List<Palette.Swatch> availablePaleteItems = palette.getSwatches();
        Palette.Swatch lightVibrant = palette.getLightVibrantSwatch();//强调色
        Palette.Swatch lightMuted = palette.getLightMutedSwatch();//轻柔色
        int pSize = availablePaleteItems.size();

//设置窗口背景为黑调色
        getWindow().setBackgroundDrawable(new ColorDrawable(palette.getDarkVibrantSwatch().getRgb()));

        //find & set text
        TextView nameTV = (TextView) findViewById(R.id.detail_tv_name);
        TextView emailTV = (TextView) findViewById(R.id.detail_tv_mail);
        TextView usernameTitleTv = (TextView) findViewById(R.id.detail_tv_nickname_title);
        TextView phoneTitleTv = (TextView) findViewById(R.id.detail_tv_phone_title);
        TextView biographyTitleTv = (TextView) findViewById(R.id.android_tv_biography_title);
        nameTV.setText(selectedUser.getName());
        emailTV.setText(selectedUser.getEmail());
      //  ((TextView) findViewById(R.id.detail_tv_phone)).setText(selectedUser.getPhone());
        ((TextView) findViewById(R.id.nickname)).setText(selectedUser.getNickname());

//设置颜色
        if (lightVibrant != null)
            nameTV.setTextColor(lightVibrant.getRgb());

        if (lightMuted != null)
            emailTV.setTextColor(lightMuted.getRgb());

        usernameTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());
        phoneTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());
        biographyTitleTv.setTextColor(availablePaleteItems.get(2 % pSize).getRgb());
    }
}
