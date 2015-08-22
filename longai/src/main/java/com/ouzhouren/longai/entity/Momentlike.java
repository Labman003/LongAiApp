package com.ouzhouren.longai.entity;

import java.sql.Timestamp;

/**
 * Momentlike entity. @author MyEclipse Persistence Tools
 */
public class Momentlike implements java.io.Serializable {

	// Fields
	private Integer momentlikeId;

	private Moment moment;
	

	private User user;
	

	private Timestamp momentlikepubtime;

	// Constructors

	/** default constructor */
	public Momentlike() {
	}

	/** full constructor */
	public Momentlike(Moment moment, User user, Timestamp momentlikepubtime) {
		this.moment = moment;
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

	public Timestamp getMomentlikepubtime() {
		return this.momentlikepubtime;
	}

	public void setMomentlikepubtime(Timestamp momentlikepubtime) {
		this.momentlikepubtime = momentlikepubtime;
	}

}