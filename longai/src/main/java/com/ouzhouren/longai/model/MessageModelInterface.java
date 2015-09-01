package com.ouzhouren.longai.model;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface MessageModelInterface {
    void getLatestMessages();
    void getUnreadCount();
    void getLocalMessages();
    void cacheLatestMessage();
    void cacheUnreadCount();
    void saveMessage();
}
