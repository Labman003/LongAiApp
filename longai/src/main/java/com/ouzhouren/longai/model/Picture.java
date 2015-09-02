package com.ouzhouren.longai.model;

public class Picture implements java.io.Serializable {

	private Integer pictureId;
	private String userId;
	private String picname;
	private long picturePubtime;
	private String originallurl;
	private String permisson;
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

	public long getPicturePubtime() {
		return picturePubtime;
	}

	public void setPicturePubtime(long picturePubtime) {
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