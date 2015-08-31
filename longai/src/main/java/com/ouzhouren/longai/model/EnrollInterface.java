package com.ouzhouren.longai.model;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface EnrollInterface {
    void enroll(String userName,String eventId,EnrollInterface.CallBack callBack);

    abstract class CallBack {
        abstract void onSuccess(Enroll enroll);
        abstract void onFail();
    }
}
