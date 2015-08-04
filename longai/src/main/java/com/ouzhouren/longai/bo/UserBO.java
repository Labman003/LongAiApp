package com.ouzhouren.longai.bo;

import android.content.Context;

import com.litesuits.http.exception.HttpException;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.StringRequest;
import com.litesuits.http.response.Response;
import com.ouzhouren.base.cache.ACache;
import com.ouzhouren.longai.common.utils.LiteHttpUtil;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.entity.User;

/**
 * Created by BenPC on 2015/8/3.
 */
public class UserBO {
    private MyLogger logger = MyLogger.benLog();
    private static String CACHE_USER = "CacheUser";
    private UserBO userBO;
    private ACache aCache;
    private Context ctx;

    private UserBO(Context ctx) {
        this.aCache = ACache.get(ctx);//创建ACache组件
        this.ctx = ctx;
    }

    public UserBO getInstance(Context ctx) {
        if (null == userBO) {
            userBO = new UserBO(ctx);
        }
        return userBO;
    }

    public boolean logon(User user) {
        String url = "192.168.1.124:8080";
        StringRequest req = new StringRequest(url).addUrlPrifix("http://").addUrlSuffix("/SignServer/ActionServlet").addUrlParam("path", "userReg").addUrlParam("user", "彭小狗");
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                logger.i("success result:" + s + "----response:" + response);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
            }
        }));
        return true;
    }

    public boolean login() {
        String url = "192.168.1.124:8080";
        StringRequest req = new StringRequest(url).addUrlPrifix("http://").addUrlSuffix("/SignServer/ActionServlet").addUrlParam("path", "userlogin").addUrlParam("name", "彭小狗").addUrlParam("pwd", "123456");
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                logger.i("success result:" + s + "----response:" + response);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
            }
        }));
        return true;
    }

    public boolean logout() {
        return true;
    }

}
