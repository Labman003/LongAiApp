package com.ouzhouren.longai.presenter;

import com.ouzhouren.longai.model.User;

import java.util.List;

/**
 * Created by BenPC on 2015/8/30.
 */
public interface SearchViewInterface {
    void refreshLovers(List<User> users);

    void showProgress();

    void dismissProgress();
    void showNodata();
    void dismissNodata();

    void showFail(String s);

    void goDetail(int position);
}
