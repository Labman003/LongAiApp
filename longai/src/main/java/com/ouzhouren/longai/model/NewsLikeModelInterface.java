package com.ouzhouren.longai.model;

import android.content.Context;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface NewsLikeModelInterface {
    void getNewsLike(int newsId, int currentPage, int amount, GetNewsLikeCallBack callBack, Context ctx);
    void sendNewsLike(NewsLike newsLike, SendNewsLikeCallBack callBack, Context ctx);

    abstract class GetNewsLikeCallBack {
        public abstract void onSuccess(List<NewsLike> newsLike,double total);
        public abstract void onFail();
    }
    abstract class SendNewsLikeCallBack {
        public abstract void onSuccess(NewsLike newsLike);
        public abstract void onFail();
    }
}
