package com.ouzhouren.longai.view.news;

import com.ouzhouren.longai.model.News;

import java.util.List;

/**
 * Created by 郭泽锋 on 2015/8/28.
 */
public interface NewsViewInterface {
    void showNews(List<News> newsList);
    void goToActivity(int itemPosition);
}
