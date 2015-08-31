package com.ouzhouren.longai.model;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface EventModelInterface {
    void getEvents(String time,String city,int pageNo,int pageSize,EventModelInterface.CallBack callBack);

    void enroll(int userId,int eventId,EventModelInterface.CallBack callBack);

    abstract class CallBack {
        public abstract void onSuccess(List<Event> events);
        public abstract void onFail();
    }
}
