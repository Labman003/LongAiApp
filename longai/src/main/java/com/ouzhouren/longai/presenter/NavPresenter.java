package com.ouzhouren.longai.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.github.pwittchen.reactivenetwork.library.ConnectivityStatus;
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;
import com.ouzhouren.base.cache.ACache;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.constant.CacheKey;
import com.ouzhouren.longai.model.PicBusinessImp;
import com.ouzhouren.longai.model.PicModelInterface;
import com.ouzhouren.longai.model.UserBusinessImp;
import com.ouzhouren.longai.model.UserModelInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by BenPC on 2015/8/31.
 */
public class NavPresenter {
    private MyLogger logger = MyLogger.benLog();

    private NavViewInterface navViewInterface;
    private UserModelInterface userModelInterface;
    private PicModelInterface picModelInterface;
    private Context ctx;

    public NavPresenter(NavViewInterface navViewInterface, Context context) {
        this.navViewInterface = navViewInterface;
        this.userModelInterface = new UserBusinessImp();
        this.picModelInterface = new PicBusinessImp();
        this.ctx = context;
    }

    public void locate(Context ctx) {
        ACache aCache = ACache.get(ctx);
        String locationdescribe = aCache.getAsString(CacheKey.LOCATION_DESCRIBE);
        if (locationdescribe != null && locationdescribe.length() > 0) {
            navViewInterface.refreshLocation(locationdescribe);
        } else {
            navViewInterface.showLocateFail();
        }
    }

    public void uploadProfilePic(Bitmap bitmap, Context mAc) {
        logger.i("beforesave");
        saveBitmapFile(bitmap);
        logger.i("aftersave");
        int userId = userModelInterface.getCacheUser(mAc).getUserId();
        picModelInterface.uploadProfilePic(userId, new File(Environment.getExternalStorageDirectory() + "/01.jpg"), new PicModelInterface.UploadCallBack() {
            @Override
            public void onSuccess() {
                navViewInterface.showUploadSuccess();
            }

            @Override
            public void onFail(String s) {
                navViewInterface.showUploadFail(s);
            }

            @Override
            public void onUpload(long total, long len) {
                navViewInterface.showUploading(total, len);
            }
        }, mAc);
    }

    //    Bitmap对象保存味图片文件
    public void saveBitmapFile(Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory() + "/01.jpg");//将要保存图片的路径
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void detectNetwork(Activity mAc) {
        new ReactiveNetwork().observeConnectivity(mAc)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter(ConnectivityStatus.isEqualTo(ConnectivityStatus.OFFLINE))
                .subscribe(new Action1<ConnectivityStatus>() {
                    @Override
                    public void call(ConnectivityStatus connectivityStatus) {
                        // do something with connectivityStatus, which will be WIFI_CONNECTED
                        navViewInterface.showNetworkDisable();
                    }
                });
        new ReactiveNetwork().observeConnectivity(mAc)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter(ConnectivityStatus.isEqualTo(ConnectivityStatus.MOBILE_CONNECTED))
                .subscribe(new Action1<ConnectivityStatus>() {
                    @Override
                    public void call(ConnectivityStatus connectivityStatus) {
                        // do something with connectivityStatus, which will be WIFI_CONNECTED
                        navViewInterface.hideNetworkDisable();
                    }
                });
        new ReactiveNetwork().observeConnectivity(mAc)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter(ConnectivityStatus.isEqualTo(ConnectivityStatus.WIFI_CONNECTED))
                .subscribe(new Action1<ConnectivityStatus>() {
                    @Override
                    public void call(ConnectivityStatus connectivityStatus) {
                        // do something with connectivityStatus, which will be WIFI_CONNECTED
                        navViewInterface.hideNetworkDisable();
                    }
                });
    }
}
