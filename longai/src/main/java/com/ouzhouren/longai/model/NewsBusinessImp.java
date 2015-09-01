package com.ouzhouren.longai.model;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.StringRequest;
import com.litesuits.http.response.Response;
import com.ouzhouren.longai.common.utils.LiteHttpUtil;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.common.utils.PageUtil;
import com.ouzhouren.longai.constant.ConstantServer;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public class NewsBusinessImp implements NewsModelInterface {
    private MyLogger logger = MyLogger.benLog();



    @Override
    public void getNews(int currentPage, int amount, final GetNewsCallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_GET_NEWS).addUrlParam("currentPage", String.valueOf(currentPage)).addUrlParam("amount", String.valueOf(amount));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                }
                logger.i("回调json" + s);
                List<News> newses = PageUtil.fetchToList(s, new TypeToken<List<News>>() {
                }.getType());
                logger.i("success result:" + s + "----response:" + response + "——Page:" + newses);
                logger.i("第一条" + newses.get(0).getContent());
                callBack.onSuccess(newses);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
                callBack.onFail();
            }
        }));
    }
}
