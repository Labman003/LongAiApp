package com.ouzhouren.longai.model;

import android.content.Context;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface MomentModelInterface {

    void sendMoment(Moment moment, SendMomentCallBack sendMomentCallBack, Context ctx);
    void deleteMoment(Moment moment, DeleteMomentCallBack deleteMomentCallBack, Context ctx);
    void getMomentsPage(int userId, int currentPage,int amount,GetPageMomentsCallBack getPageMomentsCallBack, Context ctx);
    abstract class SendMomentCallBack {
        public abstract void onSuccess(Moment moment);
        public abstract void onFail();
    }
    abstract class DeleteMomentCallBack {
        public abstract void onSuccess(int deletedId);
        public abstract void onFail();
    }

    abstract class GetPageMomentsCallBack {
        public abstract void onSuccess(List<Moment> moments);
        public abstract void onFail();
    }
}
