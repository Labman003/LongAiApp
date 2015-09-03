package com.ouzhouren.longai.model;

import android.content.Context;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface UserModelInterface {
    void login(String userName, String password, CallBack callBack, Context ctx);
    void register(String userName, String password, CallBack callBack, Context ctx);
    void cacheUser(User user, Context ctx);
    User getCacheUser(Context ctx);
    void updateUserInfo(User user ,UpdateCallBack callBack, Context ctx);
    void getUsersByGens(int gens ,GetUsersByGensCallBack callBack, Context ctx);

    abstract class CallBack {
        public abstract void onSuccess(User user);
        public abstract void onFail(String e);
    }
    abstract class UpdateCallBack {
        public abstract void onSuccess(int userId);
        public abstract void onFail(String e);
    }
    abstract class GetUsersByGensCallBack {
        public abstract void onSuccess(List<User> users);
        public abstract void onFail(String e);
    }
}
