package com.ouzhouren.longai.presenter;

import android.content.Context;

import com.ouzhouren.longai.model.Enroll;
import com.ouzhouren.longai.model.Event;
import com.ouzhouren.longai.model.EventBusinessImp;
import com.ouzhouren.longai.model.EventModelInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public class EventPresenter {
    private  EventModelInterface eventModelInterface;
    private Context ctx;
    public static List<Event> events = new ArrayList<Event>();
    private  EventViewInterface eventViewInterface;
    private  EventDetailViewInterface eventDetailViewInterface;
    private static String city;
    private static long time;
    private static int pageNo = 1;
    private static int pageSize = 10;

    public EventPresenter(Context ctx) {
        this.eventModelInterface = new EventBusinessImp();
        this.ctx = ctx;
    }


    public void setEventViewInterface(EventViewInterface eventViewInterface) {
        this.eventViewInterface = eventViewInterface;
    }

    public void setEventDetailViewInterface(EventDetailViewInterface eventDetailViewInterface) {
        this.eventDetailViewInterface = eventDetailViewInterface;
    }

    public void addEvents( Context ctx) {
        city = eventViewInterface.getCity();
        time = eventViewInterface.getTime();
        eventViewInterface.showProgress();
        eventModelInterface.getEvents(time, city, pageNo, pageSize, new EventModelInterface.GetEventsCallBack() {
            @Override
            public void onSuccess(List<Event> events) {
                eventViewInterface.refreshEvents(city,events,String.valueOf(time));
            }

            @Override
            public void onFail(String s) {

            }


        }, ctx);
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
            public void onFail(String s) {
                eventDetailViewInterface.dismissProgress();
                eventDetailViewInterface.showEnrollFail();

            }
        }, ctx);
    }

    public void restore() {
        if(events.size()==0){
            eventViewInterface.showNodata();
        }
        else {
            eventViewInterface.dismissNodata();
            eventViewInterface.refreshEvents(city, events, String.valueOf(time));
        }
    }
}
