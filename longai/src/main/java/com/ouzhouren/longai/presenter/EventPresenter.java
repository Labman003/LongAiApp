package com.ouzhouren.longai.presenter;

import android.content.Context;

import com.ouzhouren.longai.common.utils.MyLogger;
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
        eventModelInterface.getEvents(234, "广州", pageNo, pageSize, new EventModelInterface.GetEventsCallBack() {
            @Override
            public void onSuccess(List<Event> mevents) {
                EventPresenter.events.addAll(mevents);
                if(mevents.size()==0){
                    eventViewInterface.showNodata();
                }
                else {
                    eventViewInterface.dismissProgress();
                    eventViewInterface.dismissNodata();
                    eventViewInterface.refreshEvents(city, mevents, String.valueOf(time));
                }
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
        MyLogger logger = MyLogger.benLog();
        logger.i("events:"+EventPresenter.events.size());
        if(EventPresenter.events.size()==0){
            eventViewInterface.showNodata();
        }
        else {
            eventViewInterface.dismissNodata();
            eventViewInterface.refreshEvents(city, EventPresenter.events, String.valueOf(time));
        }
    }
}
