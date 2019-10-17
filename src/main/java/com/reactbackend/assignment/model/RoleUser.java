package com.reactbackend.assignment.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class RoleUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	@OneToMany(mappedBy = "roles")
	private Set<UserRegisteration> userDetails;
	
	 @Column( unique=true)
	private String roleName;
	
	public int getRoleId() {
		return roleId;
	}
	public Set<UserRegisteration> getUser() {
		return userDetails;
	}
	public void setUser(Set<UserRegisteration> userDetails) {
		this.userDetails = userDetails;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


}
