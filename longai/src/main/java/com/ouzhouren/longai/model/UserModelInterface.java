package com.ouzhouren.longai.model;

import android.content.Context;

import com.litesuits.http.exception.HttpException;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface UserModelInterface {
    void login(String userName, String password, CallBack callBack, Context ctx);
    void register(String userName, String password, CallBack callBack, Context ctx);
    void uploadProfilePic(CallBack callBack, Context ctx);
    void updateUserInfo(CallBack callBack, Context ctx);

    abstract class CallBack {
        public abstract void onSuccess(User user);
        public abstract void onFail(HttpException e);
    }
}
