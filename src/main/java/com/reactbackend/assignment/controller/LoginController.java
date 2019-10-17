package com.reactbackend.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.reactbackend.assignment.dto.LoginDto;
import com.reactbackend.assignment.model.UserRegisteration;
import com.reactbackend.assignment.service.UserRegisterationServiceImpl;

@RestController
public class LoginController {
	@Autowired
	private UserRegisterationServiceImpl userRegService;

	@PostMapping("/login")
	@CrossOrigin(origins = { "http://localhost:3000" })
	public @ResponseBody ResponseEntity login(@RequestBody LoginDto loginDto) {
	
		return userRegService.login(loginDto);
		// return new ResponseEntity<String>("Logged in", HttpStatus.OK);
	}

	@PostMapping("/getPersonalInfo")
	@CrossOrigin(origins = { "http://localhost:3000" })
	public @ResponseBody ResponseEntity getPersonalInfo(@RequestBody LoginDto loginDto) {

		UserRegisteration usertDetails = userRegService.getPersonalInfo(loginDto);
		return new ResponseEntity(usertDetails, HttpStatus.OK);

		// return new ResponseEntity<String>("Logged in", HttpStatus.OK);
	}

}
