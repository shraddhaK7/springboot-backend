package com.reactbackend.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reactbackend.assignment.model.RoleUser;
import com.reactbackend.assignment.service.RoleServiceImpl;

@RestController
public class RoleController {
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@PostMapping("/role/addRole")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity<String> createNewRole(@RequestBody RoleUser roleDetails) {
		roleService.createRole(roleDetails);
	
		return new ResponseEntity("Role Created Successfully", HttpStatus.CREATED);
	}
	@GetMapping("/role")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity<RoleUser> getRole() {
		List<RoleUser> roleDetails=  roleService.getRole();
	
		return new ResponseEntity(roleDetails, HttpStatus.OK);
	}


}
