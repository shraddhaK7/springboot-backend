package com.reactbackend.assignment.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class UserRegisteration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	 @Column( unique=true)
	private String userName;
	private String authorized;
	private String emailId;
	
	private String password;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name = "rId")
	private RoleUser roles;

	public String getAuthorized() {
		return authorized;
	}

	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UserRegisteration [uid=" + uid + ", userName=" + userName + ", emailId=" + emailId + ", password="
				+ password + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRoles(RoleUser roles) {
		this.roles = roles;
	}

	public RoleUser getRoles() {
		return roles;
}






	
}
