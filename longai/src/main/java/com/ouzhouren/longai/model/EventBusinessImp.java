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
public class EventBusinessImp implements com.ouzhouren.longai.model.EventModelInterface {
    private MyLogger logger = MyLogger.benLog();

    @Override
    public void getEvents(long time, String city, int currentPage, int amount, final GetEventsCallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_GET_EVENT).addUrlParam("starttime", String.valueOf(time)).addUrlParam("currentPage", String.valueOf(currentPage)).addUrlParam("location",city).addUrlParam("amount", String.valueOf(amount));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail("木有更多活动了");
                }
                logger.i("回调json" + s);
                List<Event> events = PageUtil.fetchToList(s, new TypeToken<List<Event>>() {
                }.getType());
                logger.i("success result:" + s + "----response:" + response + "——Page:" + events);
                logger.i("第一条" + events.get(0).getLocation());
                callBack.onSuccess(events);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                logger.i("faile exception:" + e + "----response:" + response);
                callBack.onFail(e.toString());
            }
        }));
    }

    @Override
    public void enroll(int userId, int eventId, final EnrollCallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_ENROLL).addUrlParam("eventId",String.valueOf(eventId)).addUrlParam("userId",String.valueOf(userId));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // todo 成功：主线程回调，反馈一个string
                if(s.length()==0){
                    callBack.onFail("报名失败，请稍后再试");
                }
                Gson gson = new Gson();
                Enroll enroll = gson.fromJson(s,Enroll.class);
              logger.i("success result:" + s + "----response:" + response+"——Enroll:"+enroll.toString());
                callBack.onSuccess(enroll);
            }

            @Override
            public void onFailure(HttpException e, Response<String> response) {
                // 失败：主线程回调，反馈异常
                //todo
                logger.i("faile exception:" + e + "----response:" + response);
                callBack.onFail(e.toString());
            }
        }));
    }
}
