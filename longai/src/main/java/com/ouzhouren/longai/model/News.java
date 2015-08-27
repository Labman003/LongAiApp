package com.ouzhouren.longai.model;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
public class News implements java.io.Serializable {

	private Integer newsId;
	private String content;
	private String author;
	private String title;
	// Constructors

	/** default constructor */
	public News() {
	}


	// Property accessors

	public Integer getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}