package com.ouzhouren.longai.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.ouzhouren.longai.model.User;
import com.ouzhouren.longai.model.UserBusinessImp;
import com.ouzhouren.longai.model.UserModelInterface;

/**
 * Created by BenPC on 2015/8/31.
 */
public class UserPresenter {
    private final UserModelInterface userModelInterface;

    private final LoginViewInterface loginViewInterface;

    public UserPresenter(LoginViewInterface viewInterface){
        this.loginViewInterface = viewInterface;
        this.userModelInterface = new UserBusinessImp();
    }

    public void login(Context ctx){
        String userName = loginViewInterface.getUserName();
        String password  = loginViewInterface.getPassword();
        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)){
            loginViewInterface.showLoginFail("请完善登录信息");
            return;
        }
        loginViewInterface.showProgress();
        userModelInterface.login(userName, password, new UserModelInterface.CallBack() {

            @Override
            public void onSuccess(User user) {
                loginViewInterface.dismissProgress();
                loginViewInterface.goToActivity();
            }

            @Override
            public void onFail() {
                loginViewInterface.showLoginFail("登陆失败，请稍后尝试");
            }
        }, ctx);
    }
}
