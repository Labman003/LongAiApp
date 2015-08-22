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
	

	private Moment moment;
	private User user;


	// Constructors

	/** default constructor */
	public Momentcomment() {
	}

	/** full constructor */
	public Momentcomment(Moment moment, User user, String momentcommentcontent,
			Timestamp momentcontentpubtime) {
		this.moment = moment;
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

	public Moment getMoment() {
		return this.moment;
	}

	public void setMoment(Moment moment) {
		this.moment = moment;
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