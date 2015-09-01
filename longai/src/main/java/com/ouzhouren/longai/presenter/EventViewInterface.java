package com.ouzhouren.longai.presenter;

import com.ouzhouren.longai.model.Event;

import java.util.List;

/**
 * Created by BenPC on 2015/8/30.
 */
public interface EventViewInterface {
    String chooseCity();
    String chooseTime();
    void showEvents(String city,List<Event> events,String time);
    void addEvents(String city,List<Event> events,String time);
    void goToEventDetail();

    void showProgress();
    void dismissProgress();
}
