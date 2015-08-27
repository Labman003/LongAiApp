package com.ouzhouren.longai.entity;

import java.sql.Timestamp;


/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message  implements java.io.Serializable {


    // Fields    

     private Integer messageSid;
     private String messageId;
     private String from;
     private String to;
     private String status;
     private Timestamp sendtime;
     private Timestamp receivetime;


    // Constructors

    /** default constructor */
    public Message() {
    }

    
    /** full constructor */
    public Message(String messageId, String from, String to, String status, Timestamp sendtime, Timestamp receivetime) {
        this.messageId = messageId;
        this.from = from;
        this.to = to;
        this.status = status;
        this.sendtime = sendtime;
        this.receivetime = receivetime;
    }

   
    // Property accessors

    public Integer getMessageSid() {
        return this.messageSid;
    }
    
    public void setMessageSid(Integer messageSid) {
        this.messageSid = messageSid;
    }

    public String getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getFrom() {
        return this.from;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }
    
    public void setTo(String to) {
        this.to = to;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getSendtime() {
        return this.sendtime;
    }
    
    public void setSendtime(Timestamp sendtime) {
        this.sendtime = sendtime;
    }

    public Timestamp getReceivetime() {
        return this.receivetime;
    }
    
    public void setReceivetime(Timestamp receivetime) {
        this.receivetime = receivetime;
    }
   








}