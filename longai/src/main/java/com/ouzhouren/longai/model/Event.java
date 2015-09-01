package com.ouzhouren.longai.model;

/**
 * Event entity. @author MyEclipse Persistence Tools
 */
public class Event implements java.io.Serializable {

	private int eventId;
	private String title;
	private String sponsors;
	private long starttime;
	private long endtime;
	private String status;
	private String contenturl;
	private String permission;
	private String location;
	private String cover;


// Constructors

	/** default constructor */
	public Event() {
	}



	// Property accessors

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSponsors() {
		return this.sponsors;
	}

	public void setSponsors(String sponsors) {
		this.sponsors = sponsors;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public long getStarttime() {
		return starttime;
	}

	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public long getEndtime() {
		return endtime;
	}

	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContenturl() {
		return this.contenturl;
	}

	public void setContenturl(String contenturl) {
		this.contenturl = contenturl;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



}