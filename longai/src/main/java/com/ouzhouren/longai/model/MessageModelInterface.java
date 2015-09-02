package com.ouzhouren.longai.model;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface MessageModelInterface {
    Message getLatestMessages(int toId);
    int getUnreadCount(int toId);
    List<Message> getLocalMessages(int toId);
    void cacheLatestMessage(Message message);
    void cacheUnreadCount(int count);
    void saveMessage(Message message);

    void initChat();
    void closeChat();
    void sendMessage(Message message);
    void acceptMessage();
}
