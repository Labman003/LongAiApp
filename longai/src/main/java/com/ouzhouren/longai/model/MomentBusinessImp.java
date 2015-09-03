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
 * Created by BenPC on 2015/8/31.
 */
public class MomentBusinessImp implements MomentModelInterface {
    private MyLogger logger = MyLogger.benLog();

    @Override
    public void sendMoment(Moment moment, final SendMomentCallBack sendMomentCallBack, Context ctx) {

        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_SEND_MOMENT).addUrlParam("userId", String.valueOf(moment.getUserId())).addUrlParam("momentContent", moment.getContent());
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    sendMomentCallBack.onFail("发送失败，请稍后再试");
                }
                logger.i("回调json" + s);
                Gson gson = new Gson();
                Moment mMoment = gson.fromJson(s, Moment.class);
                logger.i("Gson组装成功");
                logger.i("success result:" + s + "----response:" + response + "——Moment:" + mMoment.toString());
                sendMomentCallBack.onSuccess(mMoment);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
                sendMomentCallBack.onFail(e.toString());
            }
        }));
    }

    @Override
    public void deleteMoment(Moment moment, final DeleteMomentCallBack deleteMomentCallBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_DELETE_MOMENT).addUrlParam("momentId", String.valueOf(moment.getMomentId()));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (Integer.valueOf(s) == 0) {
                    deleteMomentCallBack.onFail("删除失败");
                }
                logger.i("回调json" + s);
                logger.i("success result:" + s + "----response:" + response + "——MomentId:" + Integer.valueOf(s));
                deleteMomentCallBack.onSuccess(Integer.valueOf(s));
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
                deleteMomentCallBack.onFail(e.toString());
            }
        }));
    }

    @Override
    public void getMomentsPage(int userId, int currentPage, int amount, final GetPageMomentsCallBack getPageMomentsCallBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_GET_MOMENT).addUrlParam("userId", String.valueOf(userId)).addUrlParam("currentPage", String.valueOf(currentPage)).addUrlParam("amount", String.valueOf(amount));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    getPageMomentsCallBack.onFail("没有更多心情了");
                }
                logger.i("回调json" + s);
                List<Moment> moments = PageUtil.fetchToList(s, new TypeToken<List<Moment>>() {
                }.getType());
                logger.i("success result:" + s + "----response:" + response + "——Page:" + moments);
                logger.i("第一条" + moments.get(0).getContent());
                getPageMomentsCallBack.onSuccess(moments);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
                getPageMomentsCallBack.onFail(e.toString());
            }
        }));
    }


}
