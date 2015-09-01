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
public class NewsCommentBusinessImp implements NewsCommentModelInterface {
    private MyLogger logger = MyLogger.benLog();



    @Override
    public void getNewsComment(int newsId, int currentPage, int amount, final GetNewsCommentCallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_GET_NEWS_COMMENT).addUrlParam("newsId",String.valueOf(newsId)).addUrlParam("currentPage", String.valueOf(currentPage)).addUrlParam("amount", String.valueOf(amount));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                }
                logger.i("回调json" + s);
                List<NewsComment> newsComments = PageUtil.fetchToList(s, new TypeToken<List<NewsComment>>() {
                }.getType());
                logger.i("success result:" + s + "----response:" + response + "——Page:" + newsComments);
                logger.i("第一条" + newsComments.get(0).getCommentContent());
                callBack.onSuccess(newsComments);
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
    public void sendNewsComment(NewsComment newsComment, final SendNewsCommentCallBack callBack, Context ctx) {
        final Gson gson = new Gson();
        String jNewsComment = gson.toJson(newsComment);
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_SEND_NEWS_COMMENT).addUrlParam("comment",jNewsComment);
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                }
                logger.i("回调json" + s);
                NewsComment receive = gson.fromJson(s,NewsComment.class);
                logger.i("success result:" + s + "----response:" + response + "——NewsComment:" + receive);
                logger.i("第一条" + receive.getCommentContent());
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
