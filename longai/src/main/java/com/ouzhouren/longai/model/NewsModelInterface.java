package com.ouzhouren.longai.model;

import android.content.Context;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface NewsModelInterface {
    void getNews(int currentPage, int amount, GetNewsCallBack callBack, Context ctx);

    abstract class GetNewsCallBack {
        public abstract void onSuccess(List<News> newses);
        public abstract void onFail();
    }
}
