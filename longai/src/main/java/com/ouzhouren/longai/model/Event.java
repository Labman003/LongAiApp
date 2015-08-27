package com.ouzhouren.longai.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Event entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="event")
public class Event implements java.io.Serializable {

	// Fields
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="event_id")
	private String eventId;
	@Column(name="title")
	private String title;
	@Column(name="sponsors")
	private String sponsors;
	@Column(name="starttime")
	private Timestamp starttime;
	@Column(name="endtime")
	private Timestamp endtime;
	@Column(name="status")
	private String status;
	@Column(name="contenturl")
	private String contenturl;
	@Column(name="permission")
	private String permission;
	@Column(name="location")
	private String location;


	// Constructors

	/** default constructor */
	public Event() {
	}



	// Property accessors

	public String getEventId() {
		return this.eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
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