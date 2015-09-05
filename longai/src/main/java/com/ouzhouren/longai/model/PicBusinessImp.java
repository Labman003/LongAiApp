package com.ouzhouren.longai.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.request.AbstractRequest;
import com.litesuits.http.request.StringRequest;
import com.litesuits.http.request.content.multi.FilePart;
import com.litesuits.http.request.content.multi.MultipartBody;
import com.litesuits.http.request.content.multi.StringPart;
import com.litesuits.http.request.param.HttpMethods;
import com.litesuits.http.response.Response;
import com.ouzhouren.longai.common.utils.LiteHttpUtil;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.common.utils.PageUtil;
import com.ouzhouren.longai.constant.ConstantServer;

import java.io.File;
import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public class PicBusinessImp implements PicModelInterface {
    private MyLogger logger = MyLogger.benLog();

    @Override
    public void uploadAlbumPic(int userId,Picture picDetail, File file, final UploadCallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_UPLOAD_PHOTO)
                .setMethod(HttpMethods.Post)
                .setHttpListener(new HttpListener<String>(true, false, true) {
                    @Override
                    public void onSuccess(String s, Response<String> response) {
                        logger.i("success result:" + s + "----response:" + response);
                        if(s.length()==0){
                          callBack.onFail("上传失败，请稍后在网络良好的地方尝试");
                            return;
                        }
                        callBack.onSuccess();
                        response.printInfo();
                    }

                    @Override
                    public void onFailure(HttpException e, Response<String> response) {
                        logger.i("success result:" + e.toString() + "----response:" + response);
                        callBack.onFail(e.toString());
                    }

                    @Override
                    public void onUploading(AbstractRequest<String> request, long total, long len) {
                        callBack.onUpload(total, len);
                    }
                });
        MultipartBody body = new MultipartBody();
        Gson gson = new Gson();
        body.addPart(new StringPart("picDetail", gson.toJson(picDetail)));
        body.addPart(new FilePart("picUpload", file));
        req.setHttpBody(body);
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req);
    }

    @Override
    public void uploadProfilePic(int userId, File file, final UploadCallBack callBack, Context ctx) {
        logger.i("upload请求发起");
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_UPLOAD_PROFILE_PIC)
                .setMethod(HttpMethods.Post)
                .setHttpListener(new HttpListener<String>(true, false, true) {
                    @Override
                    public void onSuccess(String s, Response<String> response) {
                        logger.i("success result:" + s + "----response:" + response);
                        if(s.length()==0){
                            callBack.onFail("上传失败，请稍后在网络良好的地方尝试");
                            return;
                        }
                        callBack.onSuccess();
                        response.printInfo();
                    }

                    @Override
                    public void onFailure(HttpException e, Response<String> response) {
                        logger.i("Upload error:" + e.toString());
                        logger.i("success result:" + e.toString() + "----response:" + response);
                        callBack.onFail(e.toString());
                    }

                    @Override
                    public void onUploading(AbstractRequest<String> request, long total, long len) {
                        logger.i("Uploading:" + total+len);
                      //  callBack.onUpload(total, len);
                    }
                });
        MultipartBody body = new MultipartBody();
        body.addPart(new StringPart("userId", String.valueOf(userId)));
        body.addPart(new FilePart("userIcon", file));
        req.setHttpBody(body);
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req);
    }

    @Override
    public void getAlbumPics(int userId, int currentPage, int amount, final GetAlbumCallBack callBack, Context ctx) {
        StringRequest req = new StringRequest(ConstantServer.HOSTNAME).addUrlPrifix(ConstantServer.PRE_FIX).addUrlSuffix(ConstantServer.PATCH_GET_ALBUM).addUrlParam("userId", String.valueOf(userId)).addUrlParam("currentPage", String.valueOf(currentPage)).addUrlParam("amount", String.valueOf(amount));
        LiteHttpUtil.getLiteHttp(ctx).executeAsync(req.setHttpListener(new HttpListener<String>() {
            @Override
            public void onSuccess(String s, Response<String> response) {
                // 成功：主线程回调，反馈一个string
                if (s.length() == 0) {
                    callBack.onFail();
                    return;
                }
                logger.i("回调json" + s);
                List<Picture> pictures = PageUtil.fetchToList(s, new TypeToken<List<Picture>>() {
                }.getType());
                logger.i("success result:" + s + "----response:" + response + "——Pictures:" + callBack);
                logger.i("第一条" + pictures.get(0).getOriginallurl());
                callBack.onSuccess(pictures);
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
