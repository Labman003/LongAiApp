package com.ouzhouren.longai.model;

import android.content.Context;

import com.google.gson.Gson;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.StringRequest;
import com.litesuits.http.response.Response;
import com.ouzhouren.longai.common.utils.LiteHttpUtil;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.constant.ConstantServer;

/**
 * Created by BenPC on 2015/8/31.
 */
public class UserBusinessImp implements UserModelInterface {
    private MyLogger logger = MyLogger.benLog();

    @Override
    public void login(String userName, String password, final CallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_LOGIN).addUrlParam("name", userName).addUrlParam("pwd",password);
      //  StringRequest req = new StringRequest("http://miao-mac.local:8080/longai/user/login?name=233&pwd=3");
      //  StringRequest req = new StringRequest("http://192.168.1.103:8080/longai/user/login?name=233&pwd=3");
        logger.i("start");
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail("用户不存在或密码错误");
                }
                logger.i("回调json"+s);
                Gson gson = new Gson();
                User user = gson.fromJson(s, User.class);
                logger.i("Gson组装成功");
                logger.i("success result:" + s + "----response:" + response + "——User:" + user.toString());
                callBack.onSuccess(user);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
                callBack.onFail(e.getMessage());
            }
        }));
    }

    @Override
    public void register(String userName, String password, final CallBack callBack, Context ctx) {
        User mUser = new User();
        Gson gson = new Gson();
        mUser.setName(userName);
        mUser.setPassword(password);
        mUser.setGender("男");
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCHP_REGISTER).addUrlParam("user", gson.toJson(mUser));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail("用户不存在或密码错误");
                }
                logger.i("回调json"+s);
                Gson gson = new Gson();
                User user = gson.fromJson(s, User.class);
                logger.i("Gson组装成功");
                logger.i("success result:" + s + "----response:" + response + "——User:" + user.toString());
                callBack.onSuccess(user);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
                callBack.onFail(e.getMessage());
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
