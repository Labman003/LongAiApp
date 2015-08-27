package com.ouzhouren.longai.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Enroll entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="enroll")
public class Enroll implements java.io.Serializable {

    // Fields
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="enroll_id")
	private Integer enrollId;
    @Column(name="event_id")
	private String eventId;
    @Column(name="user_id")
	private String userId;

	// Constructors

	/** default constructor */
	public Enroll() {
	}

	/** full constructor 
	 * @param eventId */
	public Enroll(String userId, Integer enrollId, String eventId) {
		this.eventId = eventId;
		this.userId = userId;
		this.enrollId = enrollId;
	}


	public String getEventId() {
		return this.eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getEnrollId() {
		return this.enrollId;
	}

	public void setEnrollId(Integer enrollId) {
		this.enrollId = enrollId;
	}

}