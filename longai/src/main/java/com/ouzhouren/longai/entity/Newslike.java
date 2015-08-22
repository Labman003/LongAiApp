package com.ouzhouren.longai.entity;

public class Newslike implements java.io.Serializable {

	// Fields
	private Integer newslikeId;
	private News news;

	private User user;
	private String newslikePubtime;

	// Constructors

	/** default constructor */
	public Newslike() {
	}

	/** full constructor */
	public Newslike(News news, User user, String newslikePubtime) {
		this.news = news;
		this.user = user;
		this.newslikePubtime = newslikePubtime;
	}

	// Property accessors

	public Integer getNewslikeId() {
		return this.newslikeId;
	}

	public void setNewslikeId(Integer newslikeId) {
		this.newslikeId = newslikeId;
	}

	public News getNews() {
		return this.news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNewslikePubtime() {
		return this.newslikePubtime;
	}

	public void setNewslikePubtime(String newslikePubtime) {
		this.newslikePubtime = newslikePubtime;
	}

}