package com.ouzhouren.longai.entity;

import java.util.Date;

/**
 * Picture entity. @author MyEclipse Persistence Tools
 */
public class Picture implements java.io.Serializable {

	// Fields
	private Integer pictureId;
	private User user;

	private String picname;
	private Date picturePubtime;

	private String originallurl;
	private String sid;
	private String permisson;
	private String nailurl;

	// Constructors

	/** default constructor */
	public Picture() {
	}

	/** full constructor */
	public Picture(User user, String picname, Date picturePubtime,
			String originallurl, String sid, String permisson, String nailurl) {
		this.user = user;
		this.picname = picname;
		this.picturePubtime = picturePubtime;
		this.originallurl = originallurl;
		this.sid = sid;
		this.permisson = permisson;
		this.nailurl = nailurl;
	}

	// Property accessors

	public Integer getPictureId() {
		return this.pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPicname() {
		return this.picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}

	public Date getPicturePubtime() {
		return this.picturePubtime;
	}

	public void setPicturePubtime(Date picturePubtime) {
		this.picturePubtime = picturePubtime;
	}

	public String getOriginallurl() {
		return this.originallurl;
	}

	public void setOriginallurl(String originallurl) {
		this.originallurl = originallurl;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPermisson() {
		return this.permisson;
	}

	public void setPermisson(String permisson) {
		this.permisson = permisson;
	}

	public String getNailurl() {
		return this.nailurl;
	}

	public void setNailurl(String nailurl) {
		this.nailurl = nailurl;
	}

}