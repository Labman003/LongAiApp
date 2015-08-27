package com.ouzhouren.longai.model;

public class MomentLike {
    private Integer momentLikeId;
	private String nickName;//点赞人
	private long pubTime;
	private Integer userId;//点赞人
	private Integer momentId;
	public Integer getMomentLikeId() {
		return momentLikeId;
	}
	public void setMomentLikeId(Integer momentLikeId) {
		this.momentLikeId = momentLikeId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
