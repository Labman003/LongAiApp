package com.ouzhouren.longai.model;

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
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user")
public class User implements java.io.Serializable{

	// Fields
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_Id")
	private Integer userId;
	@Column(name="name")
	private String name;
	@Column(name="nickname")
	private String nickname;
	@Column(name="email")
	private String email;
	@Column(name="location")
	private String location;
	@Column(name="biography")
	private String biography;
	@Column(name="profilepic")
	private String profilepic;
	@Column(name="gender")
	private String gender;
	@Column(name="type")
	private String type;
	@Column(name="preferencegens")
	private Double preferencegens;
	@Column(name="selfgens")
	private Double selfgens;
	@Column(name="password")
	private String password;
	


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


}