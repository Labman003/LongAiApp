package com.ouzhouren.longai.entity;

import java.sql.Timestamp;

/**
 * Momentcomment entity. @author MyEclipse Persistence Tools
 */
public class Momentcomment implements java.io.Serializable {

    // Fields
    private Integer momentcommentId;
    private String momentcommentcontent;
    private Timestamp momentcontentpubtime;
    private Integer userId;
    private String nickName;
    private Integer momentId;
    private User user;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    // Constructors

    /**
     * default constructor
     */
    public Momentcomment() {
    }

    /**
     * full constructor
     */
    public Momentcomment(Integer momentId, User user, String momentcommentcontent,
                         Timestamp momentcontentpubtime) {
        this.momentId = momentId;
        this.user = user;
        this.momentcommentcontent = momentcommentcontent;
        this.momentcontentpubtime = momentcontentpubtime;
    }

    // Property accessors

    public Integer getMomentcommentId() {
        return this.momentcommentId;
    }

    public void setMomentcommentId(Integer momentcommentId) {
        this.momentcommentId = momentcommentId;
    }

    public Integer getMomentId() {
        return this.momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMomentcommentcontent() {
        return this.momentcommentcontent;
    }

    public void setMomentcommentcontent(String momentcommentcontent) {
        this.momentcommentcontent = momentcommentcontent;
    }

    public Timestamp getMomentcontentpubtime() {
        return this.momentcontentpubtime;
    }

    public void setMomentcontentpubtime(Timestamp momentcontentpubtime) {
        this.momentcontentpubtime = momentcontentpubtime;
    }

}