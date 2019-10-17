package com.reactbackend.assignment.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactbackend.assignment.model.RoleUser;
import com.reactbackend.assignment.repository.RoleRespository;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRespository roleRepos; 
	public void createRole(RoleUser roleDetails) {
		roleRepos.save(roleDetails);
	}
	@Override
	public List<RoleUser> getRole() {
		// TODO Auto-generated method stub
		return (List<RoleUser>) roleRepos.findAll();
		 
	}
	public Optional<RoleUser> getAllRoleByRoleId(int roleId) {
		return roleRepos.findById(roleId);
	}

}
