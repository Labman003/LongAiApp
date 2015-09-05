package com.ouzhouren.longai.model;

import android.content.Context;

import java.util.List;

/**
 * Created by 郭泽锋 on 2015/9/2.
 */
public interface MomentLikeModelInterface {
    void getMomentLike(int momentId, int currentPage, int amount, GetMomentLikeCallBack callBack, Context ctx);
    void sendMomentLike(MomentLike momentLike, SendMomentLikeCallBack callBack, Context ctx);

    abstract class GetMomentLikeCallBack {
        public abstract void onSuccess(List<MomentLike> momentLikes,double total);
        public abstract void onFail();
    }
    abstract class SendMomentLikeCallBack {
        public abstract void onSuccess(MomentLike momentLike);
        public abstract void onFail();
    }
}
