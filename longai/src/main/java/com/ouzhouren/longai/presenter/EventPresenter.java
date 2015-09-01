package com.ouzhouren.longai.presenter;

import android.content.Context;

import com.ouzhouren.longai.model.Enroll;
import com.ouzhouren.longai.model.EventBusinessImp;
import com.ouzhouren.longai.model.EventModelInterface;

/**
 * Created by BenPC on 2015/8/31.
 */
public class EventPresenter {
    private  EventModelInterface eventModelInterface;

    private  EventViewInterface eventViewInterface;
    private  EventDetailViewInterface eventDetailViewInterface;

    public EventPresenter() {
        this.eventModelInterface = new EventBusinessImp();
    }


    public void setEventViewInterface(EventViewInterface eventViewInterface) {
        this.eventViewInterface = eventViewInterface;
    }

    public void setEventDetailViewInterface(EventDetailViewInterface eventDetailViewInterface) {
        this.eventDetailViewInterface = eventDetailViewInterface;
    }

    public void addEvents(int pageNo, int pageSize, Context ctx) {
        String city = eventViewInterface.chooseCity();
        String time = eventViewInterface.chooseTime();
        eventViewInterface.showProgress();
//        eventModelInterface.addEvents(time, city, pageNo, pageSize, new EventModelInterface.AddEventCallBack() {
//            @Override
//            public void onSuccess(List<Event> events) {
//
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        }, ctx);
    }
    public void enroll(int eventId, Context ctx) {
        //todo 获取userid
        int userId = 3;
        eventDetailViewInterface.showProgress();
        eventModelInterface.enroll(userId, eventId, new EventModelInterface.EnrollCallBack() {

            @Override
            public void onSuccess(Enroll enroll) {

            }

            @Override
            public void onFail() {
                eventDetailViewInterface.dismissProgress();
                eventDetailViewInterface.showEnrollFail();

            }
        }, ctx);
    }
}
