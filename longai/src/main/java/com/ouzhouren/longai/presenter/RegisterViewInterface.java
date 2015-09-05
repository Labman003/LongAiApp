package com.ouzhouren.longai.presenter;

/**
 * Created by BenPC on 2015/8/30.
 */
public interface RegisterViewInterface {
    String getUserName();
    String getPassword();

    String getNickName();

    void showProgress();
    void dismissProgress();
    void showRegisterFail(String error);
    void goToActivity();
}
