package com.ouzhouren.longai.entity;

public class User implements java.io.Serializable{

	// Fields
	private Integer userSid;
	private String name;
	private String nickname;
	private String email;
	private String location;
	private String biography;
	private String profilepic;
	private String gender;
	private String type;
	private String phone;
	private Double preferencegens;
	private Double selfgens;
	private String userId;
	private String password;
	


	// Constructors

	/** default constructor */
	public User() {
	}


	// Property accessors

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserSid() {
		return this.userSid;
	}

	public void setUserSid(Integer userSid) {
		this.userSid = userSid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBiography() {
		return this.biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getProfilepic() {
		return this.profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPreferencegens() {
		return this.preferencegens;
	}

	public void setPreferencegens(Double preferencegens) {
		this.preferencegens = preferencegens;
	}

	public Double getSelfgens() {
		return this.selfgens;
	}

	public void setSelfgens(Double selfgens) {
		this.selfgens = selfgens;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}