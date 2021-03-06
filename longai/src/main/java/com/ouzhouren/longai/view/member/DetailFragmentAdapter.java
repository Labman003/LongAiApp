package com.ouzhouren.longai.view.member;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.ouzhouren.longai.model.User;

import java.util.List;

/**
 * Created by BenPC on 2015/8/11.
 */
public class DetailFragmentAdapter extends FragmentStatePagerAdapter {
    private List<User> users ;
    public DetailFragmentAdapter(FragmentManager fm,List<User> users) {
        super(fm);
        this.users = users;
    }

    @Override
    public Fragment getItem(int position) {
        DetailFragment f = new DetailFragment();
        return f;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        DetailFragment f = (DetailFragment) super.instantiateItem(container, position);
        User user = users.get(position);
        f.setUser(user);
        f.setPosition(position);
        f.setSize(users.size());
        return f;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
