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
public class NewsLikeBusinessImp implements NewsLikeModelInterface {
    private MyLogger logger = MyLogger.benLog();

    @Override
    public void getNewsLike(int newsId, int currentPage, int amount, final GetNewsLikeCallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_GET_NEWS_LIKE).addUrlParam("newsId",String.valueOf(newsId)).addUrlParam("currentPage", String.valueOf(currentPage)).addUrlParam("amount", String.valueOf(amount));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                }
                logger.i("回调json" + s);
                List<NewsLike> newsLikes = PageUtil.fetchToList(s, new TypeToken<List<NewsLike>>() {
                }.getType());
                logger.i("success result:" + s + "----response:" + response + "——Page:" + newsLikes);
                logger.i("第一条" + newsLikes.get(0).getNickname());
                Gson gson = new Gson();
                Page page = gson.fromJson(s,Page.class);
                callBack.onSuccess(newsLikes,page.getTotal());
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
    public void sendNewsLike(NewsLike newsLike, final SendNewsLikeCallBack callBack, Context ctx) {
        final Gson gson = new Gson();
        String jNewsLike = gson.toJson(newsLike);
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_SEND_NEWS_LIKE).addUrlParam("newsLike",jNewsLike);
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                }
                logger.i("回调json" + s);
                NewsLike receive = gson.fromJson(s,NewsLike.class);
                logger.i("success result:" + s + "----response:" + response + "——NewsLike:" + receive);
                logger.i("第一条" + receive.getNickname());
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
