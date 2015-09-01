package com.ouzhouren.longai.presenter;

/**
 * Created by BenPC on 2015/8/30.
 */
public interface EventDetailViewInterface {
    void showProgress();

    void showEnrollFail();
    void showEnrollSuccess();

    void enroll();

    void showEventDetail();

    void dismissProgress();
}
