package com.ouzhouren.longai.model;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User implements java.io.Serializable{

	private Integer userId;
	private String name;
	private String nickname;
	private String email;
	private String location;
	private String biography;
	private String profilepic;
	private String gender;
	private String type;
	private Double preferencegens;
	private Double selfgens;
	private String password;
	private int phone;


	// Constructors

	/** default constructor */
	public User() {
	}


	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getPhone() {
		return phone;
	}
}