package com.ouzhouren.longai.model;

import android.content.Context;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface MomentCommentModelInterface {
    void getMomentComment(int MomentId, int currentPage, int amount, GetMomentCommentCallBack callBack, Context ctx);
    void sendMomentComment(MomentComment MomentComment, SendMomentCommentCallBack callBack, Context ctx);
    void deleteMomentComment(MomentComment MomentComment, DeleteMomentCommentCallBack callBack, Context ctx);

    abstract class GetMomentCommentCallBack {
        public abstract void onSuccess(List<MomentComment> MomentComments);
        public abstract void onFail();
    }
    abstract class SendMomentCommentCallBack {
        public abstract void onSuccess(MomentComment MomentComment);
        public abstract void onFail();
    }
    abstract class DeleteMomentCommentCallBack {
        public abstract void onSuccess(int deletedMomentCommentId);
        public abstract void onFail();
    }
}
