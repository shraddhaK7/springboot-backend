package com.reactbackend.assignment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.reactbackend.assignment.dto.LoginDto;
import com.reactbackend.assignment.model.UserRegisteration;


public interface UserRegisterationService {
	
	void saveUser(int roleId, UserRegisteration userDetails);
	List<UserRegisteration> getRegisteredUsers();
	/*String getUserVerification(String userName, String password);*/
	UserRegisteration findByUsername(String username);
	ResponseEntity login(LoginDto loginDto);
/*	UserRegisteration findByUserType(Boolean userType);*/
	List<UserRegisteration> findByAuthorized(String userType);
	UserRegisteration approveUser(LoginDto loginDto);
	UserRegisteration getPersonalInfo(LoginDto loginDto);
	void changeRole(String userName, int roleId);
}
