package com.ouzhouren.longai.model;

import android.content.Context;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface NewsCommentModelInterface {
    void getNewsComment(int newsId,int currentPage, int amount, GetNewsCommentCallBack callBack, Context ctx);
    void sendNewsComment(NewsComment newsComment, SendNewsCommentCallBack callBack, Context ctx);

    abstract class GetNewsCommentCallBack {
        public abstract void onSuccess(List<NewsComment> newsComments);
        public abstract void onFail();
    }
    abstract class SendNewsCommentCallBack {
        public abstract void onSuccess(NewsComment newsComment);
        public abstract void onFail();
    }
}
