package com.ouzhouren.longai.presenter;

import com.ouzhouren.longai.model.Event;
import com.ouzhouren.longai.model.EventModelInterface;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public class EventPresenter {
    private  EventModelInterface eventModelInterface;

    private  EventViewInterface eventViewInterface;
    private  EventDetailViewInterface eventDetailViewInterface;

    public EventPresenter() {
    }

    public void setEventModelInterface(EventModelInterface eventModelInterface) {
        this.eventModelInterface = eventModelInterface;
    }

    public void setEventViewInterface(EventViewInterface eventViewInterface) {
        this.eventViewInterface = eventViewInterface;
    }

    public void setEventDetailViewInterface(EventDetailViewInterface eventDetailViewInterface) {
        this.eventDetailViewInterface = eventDetailViewInterface;
    }

    public void addEvents(int pageNo, int pageSize) {
        String city = eventViewInterface.chooseCity();
        String time = eventViewInterface.chooseTime();
        eventViewInterface.showProgress();
        eventModelInterface.getEvents(time, city, pageNo, pageSize, new EventModelInterface.CallBack() {
            @Override
            public void onSuccess(List<Event> events) {

            }

            @Override
            public void onFail() {

            }
        });
    }
    public void enroll(int eventId) {
        //todo 获取userid
        int userId = 3;
        eventDetailViewInterface.showProgress();
        eventModelInterface.enroll(userId, eventId, new EventModelInterface.CallBack() {
            @Override
            public void onSuccess(List<Event> events) {
                eventDetailViewInterface.dismissProgress();
            }

            @Override
            public void onFail() {
                eventDetailViewInterface.dismissProgress();
                eventDetailViewInterface.showEnrollFail();

            }
        });
    }
}
