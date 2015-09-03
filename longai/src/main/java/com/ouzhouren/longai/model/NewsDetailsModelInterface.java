package com.ouzhouren.longai.model;

import android.content.Context;

/**
 * Created by 郭泽锋 on 2015/9/1.
 */
public interface NewsDetailsModelInterface {
    void getNewsDetailsInfo(int currentPage, int amount, final NewsModelInterface.GetNewsCallBack callBack, Context ctx);

    abstract class CallBack {
        public abstract void onSuccess(News news);
        public abstract void onFail();
    }
}
