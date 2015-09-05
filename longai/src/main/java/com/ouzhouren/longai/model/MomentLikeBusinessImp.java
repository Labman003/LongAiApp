package com.ouzhouren.longai.model;

import android.content.Context;

import com.google.gson.Gson;
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
 * Created by 郭泽锋 on 2015/9/2.
 */
public class MomentLikeBusinessImp implements MomentLikeModelInterface {
    MyLogger logger = MyLogger.benLog();

    @Override
    public void getMomentLike(int momentLikeId, int currentPage, int amount, final GetMomentLikeCallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_GET_NEWS_LIKE).addUrlParam("momentLikeId", String.valueOf(momentLikeId)).addUrlParam("currentPage", String.valueOf(currentPage)).addUrlParam("amount", String.valueOf(amount));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                }
                logger.i("回调json" + s);
                List<MomentLike> momentLikes = PageUtil.fetchToList(s, new TypeToken<List<MomentLike>>() {
                }.getType());
                logger.i("success result:" + s + "----response:" + response + "——Page:" + momentLikes);
                logger.i("第一条" + momentLikes.get(0).getNickName());
                Gson gson = new Gson();
                Page page = gson.fromJson(s, Page.class);
                callBack.onSuccess(momentLikes, page.getTotal());
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
    public void sendMomentLike(MomentLike momentLike, final SendMomentLikeCallBack callBack, Context ctx) {
        final Gson gson = new Gson();
        String jMomentLike = gson.toJson(momentLike);
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_SEND_NEWS_LIKE).addUrlParam("momentLike", jMomentLike);
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                }
                logger.i("回调json" + s);
                MomentLike receive = gson.fromJson(s, MomentLike.class);
                logger.i("success result:" + s + "----response:" + response + "——momentLike:" + receive);
                logger.i("第一条" + receive.getNickName());
                callBack.onSuccess(receive);
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

