package com.ouzhouren.longai.presenter;

import com.ouzhouren.longai.model.Event;

import java.util.List;

/**
 * Created by BenPC on 2015/8/30.
 */
public interface EventViewInterface {
    void chooseCity();
    void chooseTime();
    String getCity();
    long getTime();

    void refreshEvents(String city,List<Event> events,String time);
    void addEvents(String city,List<Event> events,String time);
    void goToEventDetail(int position);
    void goToEventDetailAndEnroll(int position);
    void showProgress();
    void dismissProgress();
    void showFail(String s);
    void showNodata();
    void dismissNodata();
}
