package com.reactbackend.assignment.service;

import java.util.List;
import java.util.Optional;

import com.reactbackend.assignment.model.RoleUser;


public interface RoleService {
	void createRole(RoleUser roleDetails);
	 List<RoleUser> getRole();
}
