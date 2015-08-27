package com.ouzhouren.longai.model;

public class MomentComment {
    private Integer momentcommentId;
	private String nickName;
	private String content;
	private long pubTime;
	private Integer userId;
	private Integer momentId;
	public Integer getMomentcommentId() {
		return momentcommentId;
	}
	public void setMomentcommentId(Integer momentcommentId) {
		this.momentcommentId = momentcommentId;
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
	public long getPubTime() {
		return pubTime;
	}
	public void setPubTime(long pubTime) {
		this.pubTime = pubTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMomentId() {
		return momentId;
	}
	public void setMomentId(Integer momentId) {
		this.momentId = momentId;
	}
}
