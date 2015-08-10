package com.ouzhouren.longai;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ouzhouren.longai.module.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragmentList;
        private List<String> titleList;
        private SearchFragment searchFragment;
        private NewsFragment newsFragment;
        private TalksFragment talksFragment;
        private  EventsFragment eventsFragment;
        public MainFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
            if(titleList == null){
                this.titleList = new ArrayList<String>();
                this.titleList.add("缘分");
                this.titleList.add("消息");
                this.titleList.add("课室");
                this.titleList.add("活动q");
            }
            if(mFragmentList == null){
                this.mFragmentList = new ArrayList<Fragment>();
                searchFragment =new SearchFragment();
                this.mFragmentList.add(searchFragment);
                talksFragment =new TalksFragment();
                this.mFragmentList.add(talksFragment);
                newsFragment = new NewsFragment();
                this.mFragmentList.add(newsFragment);
                eventsFragment =new EventsFragment();
                this.mFragmentList.add(eventsFragment);
            }

        }

        public Fragment getFragement(int position) {
            if (mFragmentList.get(position) != null) {
               return mFragmentList.get(position);
            }
            return null;
        }
        @Override
        public Fragment getItem(int position) {
            if (mFragmentList.get(position) != null) {
                return mFragmentList.get(position);
            }
            return null;
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }