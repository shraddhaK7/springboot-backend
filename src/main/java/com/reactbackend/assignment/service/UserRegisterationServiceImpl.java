package com.reactbackend.assignment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.reactbackend.assignment.dto.LoginDto;
import com.reactbackend.assignment.model.RoleUser;
import com.reactbackend.assignment.model.UserRegisteration;
import com.reactbackend.assignment.repository.UserRegisterationRepository;

@Service
public class UserRegisterationServiceImpl implements UserRegisterationService {

	@Autowired
	private UserRegisterationRepository userRegiRepository;

	@Autowired
	private RoleServiceImpl roleService;

	private static final Logger logger = LogManager.getLogger(UserRegisterationServiceImpl.class);

	@Override
	public void saveUser(int roleId, UserRegisteration userDetails) {

		Optional<RoleUser> roleDetails = roleService.getAllRoleByRoleId(roleId);
		if (roleDetails.isPresent()) {
			userDetails.setRoles(roleDetails.get());
		}
		userRegiRepository.save(userDetails);
	}

	@Override
	public List<UserRegisteration> getRegisteredUsers() {
		// TODO Auto-generated method stub
		List<UserRegisteration> userDetail = new ArrayList<>();
		userRegiRepository.findAll().forEach(userDetail::add);
		logger.info("getRegisteredUsers() in UserRegisterationServiceImpl");
		return userDetail;
	}

	@Override
	public UserRegisteration findByUsername(String username) {
		// TODO Auto-generated method stub
		UserRegisteration user = userRegiRepository.findByUserName(username);
		return user;

	}

	@Override
	public ResponseEntity login(LoginDto loginDto) {
		// TODO Auto-generated method stub
		UserRegisteration user = userRegiRepository.findByUserName(loginDto.getUserName());

		if (user == null) {
			return new ResponseEntity("User not found.", HttpStatus.BAD_REQUEST);
		}
		if (!user.getPassword().equals(loginDto.getPassword())) {
			// throw new RuntimeException("Password mismatch.");
			return new ResponseEntity("Password incorrect.", HttpStatus.BAD_REQUEST);
		}

		if (!user.getAuthorized().equals("Yes")) {
			/*
			 * throw new
			 * RuntimeException("User is not approved. Please contact to Administator.");
			 */ return new ResponseEntity("User is not approved. Please contact to Administator.",
					HttpStatus.FORBIDDEN);
		}

		if (user != null) {
			HashMap<String, String> hashmap = new HashMap<String, String>();
			System.out.println("ashmap " + user.getUserName() + user.getRoles().getRoleName());
			// Mapping string values to int keys
			hashmap.put("UserName", user.getUserName());
			hashmap.put("role", user.getRoles().getRoleName());

			return ResponseEntity.ok().body(hashmap);
		} else {

			// throw new RuntimeException("User does not exist.");
			return new ResponseEntity("User does not exist.", HttpStatus.BAD_REQUEST);

		}

		// new ResponseEntity(loginDto, HttpStatus.OK) ;

	}

	public List<UserRegisteration> getUserType(String authorized) {
		List<UserRegisteration> userDetail = new ArrayList<>();
		userRegiRepository.findByauthorized(authorized).forEach(userDetail::add);

		return userDetail;

	}

	@Override
	public List<UserRegisteration> findByAuthorized(String userType) {
		// TODO Auto-generated method stub
		// UserRegisteration user = userRegiRepository.findByauthorized(userType);
		List<UserRegisteration> userDetail = null;
		userRegiRepository.findByauthorized(userType).forEach(userDetail::add);

		return userDetail;
	}

	@Override
	public UserRegisteration approveUser(LoginDto loginDto) {
		// TODO Auto-generated method stublogin.
		System.out.println(loginDto.getUserName());
		UserRegisteration user = userRegiRepository.findByUserName(loginDto.getUserName());
		if (user != null) {
			user.setAuthorized("Yes");
		}
		updateUser(user);

		return user;
	}

	public void updateUser(UserRegisteration user) {
		userRegiRepository.save(user);
	}

	@Override
	public UserRegisteration getPersonalInfo(LoginDto loginDto) {
		// TODO Auto-generated method stub
		UserRegisteration user = userRegiRepository.findByUserName(loginDto.getUserName());

		HashMap<String, String> hashmap = new HashMap<String, String>();

		// Mapping string values to int keys
		hashmap.put("UserName", user.getUserName());
		hashmap.put("role", user.getRoles().getRoleName());
		hashmap.put("emailId", user.getEmailId());
		hashmap.put("authorized", user.getAuthorized());
		hashmap.put("password", user.getPassword());

		return user;
		// new ResponseEntity(loginDto, HttpStatus.OK) ;

	}

	public void changeRole(String userName, int roleId) {
		UserRegisteration user = userRegiRepository.findByUserName(userName);
		if (user != null) {
			Optional<RoleUser> roleDetails = roleService.getAllRoleByRoleId(roleId);
			if (roleDetails.isPresent()) {
				user.setRoles(roleDetails.get());
			}
		}
		userRegiRepository.save(user);

	}

}
