package com.ouzhouren.longai.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Picture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="picture")
public class Picture implements java.io.Serializable {

	// Fields
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="picture_id")
	private Integer pictureId;
    @Column(name="user_id")
	private String userId;
	@Column(name="picname")
	private String picname;
	@Column(name="picture_pubtime")
	private Date picturePubtime;
	@Column(name="originallurl")
	private String originallurl;
	@Column(name="permisson")
	private String permisson;
	@Column(name="nailurl")
	private String nailurl;

	// Constructors

	/** default constructor */
	public Picture() {
	}

	// Property accessors

	public Integer getPictureId() {
		return this.pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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