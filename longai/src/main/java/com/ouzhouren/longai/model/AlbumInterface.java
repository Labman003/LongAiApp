package com.ouzhouren.longai.model;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface AlbumInterface {
    void getPics(int userId,AlbumInterface.CallBack callBack);

    abstract class CallBack {
        abstract void onSuccess(List<Picture> pictures);
        abstract void onFail();
    }
}
