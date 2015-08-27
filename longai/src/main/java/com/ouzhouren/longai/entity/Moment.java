package com.ouzhouren.longai.entity;

import java.sql.Timestamp;

/**
 * Moment entity. @author MyEclipse Persistence Tools
 */

/**
 * Momentlike entity. @author MyEclipse Persistence Tools
 */
public class Moment implements java.io.Serializable {

	// Fields
	private Integer momentId;
	private String momentContent;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private Integer userId;
	private Timestamp momentPubtime;
	
	// Constructors

	/** default constructor */
	public Moment() {
	}

	// Property accessors

	public Integer getMomentId() {
		return this.momentId;
	}

	public void setMomentId(Integer momentId) {
		this.momentId = momentId;
	}

	public String getMomentContent() {
		return this.momentContent;
	}

	public void setMomentContent(String momentContent) {
		this.momentContent = momentContent;
	}

	public Timestamp getMomentPubtime() {
		return this.momentPubtime;
	}

	public void setMomentPubtime(Timestamp momentPubtime) {
		this.momentPubtime = momentPubtime;
	}
}