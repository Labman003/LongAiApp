package com.ouzhouren.longai.model;

import android.content.Context;

import java.io.File;
import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface PicModelInterface {
    void uploadAlbumPic(int userId, File f,UploadCallBack callBack, Context ctx);

    void uploadProfilePic(int userId, File f,UploadCallBack callBack, Context ctx);

    void getAlbumPics(int userId, int currentPage,int amount,GetAlbumCallBack callBack, Context ctx);

    abstract class UploadCallBack {
        public abstract void onSuccess();

        public abstract void onFail();
    }
    abstract class GetAlbumCallBack {
        public abstract void onSuccess(List<Picture> pictures);

        public abstract void onFail();
    }

}
