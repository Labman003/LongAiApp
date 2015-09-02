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
public class MomentCommentBusinessImp implements MomentCommentModelInterface {
    private MyLogger logger = MyLogger.benLog();



    @Override
    public void getMomentComment(int momentId, int currentPage, int amount, final GetMomentCommentCallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_GET_MOMENT_COMMENT).addUrlParam("momentId",String.valueOf(momentId)).addUrlParam("currentPage", String.valueOf(currentPage)).addUrlParam("amount", String.valueOf(amount));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                }
                logger.i("回调json" + s);
                List<MomentComment> MomentComments = PageUtil.fetchToList(s, new TypeToken<List<MomentComment>>() {
                }.getType());
                logger.i("success result:" + s + "----response:" + response + "——Page:" + MomentComments);
                logger.i("第一条" + MomentComments.get(0).getContent());
                callBack.onSuccess(MomentComments);
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
    public void sendMomentComment(MomentComment momentComment, final SendMomentCommentCallBack callBack, Context ctx) {
        final Gson gson = new Gson();
        String jMomentComment = gson.toJson(momentComment);
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_SEND_MOMENT_COMMENT).addUrlParam("comment",jMomentComment).addUrlParam("userId",String.valueOf(momentComment.getUserId()));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                }
                logger.i("回调json" + s);
                MomentComment receive = gson.fromJson(s,MomentComment.class);
                logger.i("success result:" + s + "----response:" + response + "——MomentComment:" + receive);
                logger.i("第一条" + receive.getContent());
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

    @Override
    public void deleteMomentComment(MomentComment momentComment, final DeleteMomentCommentCallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_DELETE_MOMENT_COMMENT).addUrlParam("momentCommentId",String.valueOf(momentComment.getMomentcommentId()));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                }
                logger.i("success result:" + s + "----response:" + response + "——MomentCommentId:" + Integer.valueOf(s));
                callBack.onSuccess(Integer.valueOf(s));
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
