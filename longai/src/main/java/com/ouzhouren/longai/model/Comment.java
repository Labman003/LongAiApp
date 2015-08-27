package com.ouzhouren.longai.model;

import java.util.Date;

/**
 * Created by 郭泽锋 on 2015/8/19.
 */
public class Comment {
    private int id;
    private int sid;
    private int newsId;
    private int userId;

    public int getZanNumb() {
        return zanNumb;
    }

    public void setZanNumb(int zanNumb) {
        this.zanNumb = zanNumb;
    }

    private int zanNumb;
    private String nickName, content;
    private Date pubTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }
}
