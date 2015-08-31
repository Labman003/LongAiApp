package com.ouzhouren.longai.model;

import android.content.Context;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.StringRequest;
import com.litesuits.http.response.Response;
import com.ouzhouren.longai.common.utils.LiteHttpUtil;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.config.ServerConfig;

/**
 * Created by BenPC on 2015/8/31.
 */
public class UserBusinessImp implements UserModelInterface {
    private MyLogger logger = MyLogger.benLog();

    @Override
    public void login(String userName, String password, final CallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ServerConfig.HOSTNAME).addUrlPrifix(ServerConfig.PRE_FIX).addUrlSuffix(ServerConfig.PATCH_LOGIN).addUrlParam("path", "userReg").addUrlParam("user", "彭小狗");
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                Gson gson = new Gson();
                User user = gson.fromJson(s,User.class);
                logger.i("success result:" + s + "----response:" + response+"——User:"+user.toString());
                callBack.onSuccess(user);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
                callBack.onFail();
            }
        }));
    }

    @Override
    public void register(String userName, String password, final CallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ServerConfig.HOSTNAME).addUrlPrifix(ServerConfig.PRE_FIX).addUrlSuffix(ServerConfig.PATCHP_REGISTER).addUrlParam("path", "userReg").addUrlParam("user", "彭小狗");
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                Gson gson = new Gson();
                User user = gson.fromJson(s,User.class);
                logger.i("success result:" + s + "----response:" + response+"——User:"+user.toString());
                callBack.onSuccess(user);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
                callBack.onFail();
            }
        }));
    }

    @Override
    public void uploadProfilePic(CallBack callBack, Context ctx) {

    }

    @Override
    public void updateUserInfo(CallBack callBack, Context ctx) {

    }
}
