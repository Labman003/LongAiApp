package com.ouzhouren.longai.model;

import android.content.Context;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface NewsLikeModelInterface {
    void getNewsLike(int newsId, int currentPage, int amount, GetNewsLikeCallBack callBack, Context ctx);
    void sendNewsLike(NewsLike newsLike, SendNewsLikeCallBack callBack, Context ctx);
    void deleteNewsLike(NewsLike newsLike, DeleteNewsLikeCallBack callBack, Context ctx);

    abstract class GetNewsLikeCallBack {
        public abstract void onSuccess(List<NewsLike> newsLike,double total);
        public abstract void onFail(String s);
    }
    abstract class SendNewsLikeCallBack {
        public abstract void onSuccess(NewsLike newsLike);
        public abstract void onFail(String s);
    }
    abstract class DeleteNewsLikeCallBack {
        public abstract void onSuccess(int deletedId);
        public abstract void onFail(String s);
    }
}
