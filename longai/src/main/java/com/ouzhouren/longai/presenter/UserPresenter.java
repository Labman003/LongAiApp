package com.ouzhouren.longai.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.model.User;
import com.ouzhouren.longai.model.UserBusinessImp;
import com.ouzhouren.longai.model.UserModelInterface;

/**
 * Created by BenPC on 2015/8/31.
 */
public class UserPresenter {
    MyLogger logger = MyLogger.benLog();
    private  UserModelInterface userModelInterface;
    private RegisterViewInterface registerViewInterface;
    private  LoginViewInterface loginViewInterface;

    public RegisterViewInterface getRegisterViewInterface() {
        return registerViewInterface;
    }

    public void setRegisterViewInterface(RegisterViewInterface registerViewInterface) {
        this.registerViewInterface = registerViewInterface;
    }

    public UserPresenter(){
        this.userModelInterface = new UserBusinessImp();
    }


    public LoginViewInterface getLoginViewInterface() {
        return loginViewInterface;
    }

    public void setLoginViewInterface(LoginViewInterface loginViewInterface) {
        this.loginViewInterface = loginViewInterface;
    }

    public void login(final Context ctx){
        logger.i("login1");
        String userName = loginViewInterface.getUserName();
        String password  = loginViewInterface.getPassword();
        logger.i("login2");
        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)){
            loginViewInterface.showLoginFail("请完善登录信息");
            return;
        }
        logger.i("login3");
        loginViewInterface.showProgress();
        logger.i("login4");
        userModelInterface.login(userName, password, new UserModelInterface.CallBack() {

            @Override
            public void onSuccess(User user) {
                userModelInterface.cacheUser(user,ctx);
                loginViewInterface.dismissProgress();
                loginViewInterface.goToActivity();
            }

            @Override
            public void onFail(String e) {
                loginViewInterface.showLoginFail("失败，请稍后再试");
            }
        }, ctx);
    }

    public void register(final Context ctx){
        String userName = registerViewInterface.getUserName();
        String password  = registerViewInterface.getPassword();
        String nickName  = registerViewInterface.getNickName();
        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)||TextUtils.isEmpty(nickName)){
            registerViewInterface.showRegisterFail("请完善注册信息");
            return;
        }
        registerViewInterface.showProgress();
        userModelInterface.register(userName,nickName, password, new UserModelInterface.CallBack() {

            @Override
            public void onSuccess(User user) {
                userModelInterface.cacheUser(user,ctx);
                logger.i("注册成功，name:"+user.getName()+"-userId:"+user.getUserId());
                registerViewInterface.dismissProgress();
                registerViewInterface.goToActivity();
            }

            @Override
            public void onFail(String e) {
                registerViewInterface.showRegisterFail("失败，请稍后再试");
            }
        }, ctx);
    }
}
