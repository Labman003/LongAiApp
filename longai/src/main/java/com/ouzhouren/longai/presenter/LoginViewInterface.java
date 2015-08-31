package com.ouzhouren.longai.presenter;

/**
 * Created by BenPC on 2015/8/30.
 */
public interface LoginViewInterface {
    String getUserName();
    String getPassword();
    void showProgress();
    void dismissProgress();
    void showLoginFail(String error);
    void goToActivity();
}
