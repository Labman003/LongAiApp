package com.ouzhouren.longai.presenter;

import android.content.Context;

import com.ouzhouren.longai.model.User;
import com.ouzhouren.longai.model.UserBusinessImp;
import com.ouzhouren.longai.model.UserModelInterface;

/**
 * Created by BenPC on 2015/8/31.
 */
public class NavPresenter {

    private NavViewInterface eventViewInterface;
    private UserModelInterface userModelInterface;
    private Context ctx;

    public NavPresenter(NavViewInterface eventViewInterface, Context context) {
        this.eventViewInterface = eventViewInterface;
        this.userModelInterface = new UserBusinessImp();
        this.ctx = context;
    }
    public void Locate(){}
    public void uploadProfilePic(){
        userModelInterface.uploadProfilePic(new UserModelInterface.CallBack() {
            @Override
            public void onSuccess(User user) {

            }

            @Override
            public void onFail(String e) {

            }
        },ctx);
    }
}
