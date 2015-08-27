package com.ouzhouren.longai.model;

public class NewsLike {
    private Integer newsLikeId;
	private Integer newsId;
	private Integer userId;
	private String nickname;
	private long pubtime;
	public Integer getNewsLikeId() {
		return newsLikeId;
	}
	public void setNewsLikeId(Integer newsLikeId) {
		this.newsLikeId = newsLikeId;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public long getPubtime() {
		return pubtime;
	}
	public void setPubtime(long pubtime) {
		this.pubtime = pubtime;
	}
}