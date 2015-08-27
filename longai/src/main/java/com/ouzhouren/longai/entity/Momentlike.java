package com.ouzhouren.longai.entity;

import java.sql.Timestamp;

/**
 * Momentlike entity. @author MyEclipse Persistence Tools
 */
public class Momentlike implements java.io.Serializable {

	// Fields
	private Integer momentlikeId;
	private Integer momentId;
	private User user;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private Integer userId;
	private Timestamp momentlikepubtime;

	// Constructors

	/** default constructor */
	public Momentlike() {
	}

	/** full constructor */
	public Momentlike(Integer momentId, User user, Timestamp momentlikepubtime) {
		this.momentId = momentId;
		this.user = user;
		this.momentlikepubtime = momentlikepubtime;
	}

	// Property accessors

	public Integer getMomentlikeId() {
		return this.momentlikeId;
	}

	public void setMomentlikeId(Integer momentlikeId) {
		this.momentlikeId = momentlikeId;
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

	public Timestamp getMomentlikepubtime() {
		return this.momentlikepubtime;
	}

	public void setMomentlikepubtime(Timestamp momentlikepubtime) {
		this.momentlikepubtime = momentlikepubtime;
	}

}