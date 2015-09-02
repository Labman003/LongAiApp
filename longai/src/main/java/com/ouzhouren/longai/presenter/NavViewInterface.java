package com.ouzhouren.longai.presenter;

import android.net.Uri;

import com.ouzhouren.longai.model.User;

/**
 * Created by BenPC on 2015/8/30.
 */
public interface NavViewInterface {
    void cropPic();
    void refreshProfilePic(Uri drawable);
    void refreshNav(User user);
    void refreshLocation(String locationdescribe);
    void quitApplication();
    void goContacts();
    void goAlbum();
    void goSettings();
    void showLocateFail();
    void showNetworkDisable();
    void hideNetworkDisable();

    void showUploadSuccess();

    void showUploadFail(String s);

    void showUploading(long total, long len);
}
