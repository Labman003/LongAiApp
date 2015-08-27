package com.ouzhouren.longai.entity;

/**
 * Enroll entity. @author MyEclipse Persistence Tools
 */
public class Enroll implements java.io.Serializable {

	// Fields
	private Integer enrollSid;
	private Event event;
	private User user;
	private Integer enrollId;

	// Constructors

	/** default constructor */
	public Enroll() {
	}

	/** full constructor */
	public Enroll(Event event, User user, Integer enrollId) {
		this.event = event;
		this.user = user;
		this.enrollId = enrollId;
	}

	// Property accessors

	public Integer getEnrollSid() {
		return this.enrollSid;
	}

	public void setEnrollSid(Integer enrollSid) {
		this.enrollSid = enrollSid;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getEnrollId() {
		return this.enrollId;
	}

	public void setEnrollId(Integer enrollId) {
		this.enrollId = enrollId;
	}

}