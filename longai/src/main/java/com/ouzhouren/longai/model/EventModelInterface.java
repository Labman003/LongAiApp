package com.ouzhouren.longai.model;

import android.content.Context;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface EventModelInterface {
    void getEvents(int time, String city, int currentPage,int amount, GetEventsCallBack callBack, Context ctx);

    void enroll(int userId, int eventId, EnrollCallBack callBack, Context ctx);

    abstract class GetEventsCallBack {
        public abstract void onSuccess(List<Event> events);
        public abstract void onFail();
    }
    abstract class EnrollCallBack {
        public abstract void onSuccess(Enroll enroll);
        public abstract void onFail();
    }
}
