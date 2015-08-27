package com.ouzhouren.longai.model;

public class NewsComment {
	private Integer newsCommentId;
	private Integer newsId;
	private Integer userId;
	private String nickname;
	private String commentContent;
	private long pubtime;
	public Integer getNewsCommentId() {
		return newsCommentId;
	}
	public void setNewsCommentId(Integer newsCommentId) {
		this.newsCommentId = newsCommentId;
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
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public long getPubtime() {
		return pubtime;
	}
	public void setPubtime(long pubtime) {
		this.pubtime = pubtime;
	}
}