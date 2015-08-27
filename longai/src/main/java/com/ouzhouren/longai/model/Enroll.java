package com.ouzhouren.longai.model;

public class Enroll implements java.io.Serializable {

    // Fields
	private Integer enrollId;
	private String eventId;
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