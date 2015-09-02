package com.ouzhouren.longai.model;

import android.content.Context;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface MomentLikeModelInterface {
    void getMomentLike(int momentId, int currentPage, int amount, GetMomentLikeCallBack callBack, Context ctx);
    void sendMomentLike(MomentLike momentLike, SendMomentLikeCallBack callBack, Context ctx);
    void deleteMomentLike(MomentLike momentLike, DeleteMomentLikeCallBack callBack, Context ctx);

    abstract class GetMomentLikeCallBack {
        public abstract void onSuccess(List<MomentLike> MomentLikes);
        public abstract void onFail();
    }
    abstract class SendMomentLikeCallBack {
        public abstract void onSuccess();
        public abstract void onFail();
    }
    abstract class DeleteMomentLikeCallBack {
        public abstract void onSuccess(int deletedMomentLikeId);
        public abstract void onFail();
    }
}
