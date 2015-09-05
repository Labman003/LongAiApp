package com.ouzhouren.longai.model;

import java.util.List;

/**
 * Created by BenPC on 2015/8/31.
 */
public interface MessageModelInterface {
    MessageVO getLatestMessages(int toId);
    int getUnreadCount(int toId);
    List<MessageVO> getLocalMessages(int toId);
    void cacheLatestMessage(MessageVO messageVO);
    void cacheUnreadCount(int count);
    void saveMessage(MessageVO messageVO);

    void initChat();
    void closeChat();
    void sendMessage(MessageVO messageVO);
    void acceptMessage();
}
