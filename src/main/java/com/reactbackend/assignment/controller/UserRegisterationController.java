package com.reactbackend.assignment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.reactbackend.assignment.dto.LoginDto;
import com.reactbackend.assignment.model.UserRegisteration;
import com.reactbackend.assignment.service.UserRegisterationService;
import com.reactbackend.assignment.service.UserRegisterationServiceImpl;

@RestController
public class UserRegisterationController {
	
/*	@Autowired
	private UserRegisterationService userRegService;
	*/
	
	@Autowired
	private UserRegisterationServiceImpl userRegService;
	
	@PostMapping("/userregisteration")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity<String> userRegisteration(@RequestBody UserRegisteration userDetails) {
		int roleId = 3;
		System.out.println(userDetails.getAuthorized());
		//By default for access user its false
		if(userDetails.getAuthorized() == null || userDetails.getAuthorized() == "") {
			userDetails.setAuthorized("No");
		}
		userRegService.saveUser(roleId, userDetails);
		return new ResponseEntity("User Registered", HttpStatus.CREATED);
	}
	@GetMapping("/getRegistredUser")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public @ResponseBody ResponseEntity<ArrayList<UserRegisteration>> getUsers() {
		
		ArrayList<UserRegisteration> usertDetails = (ArrayList<UserRegisteration>) userRegService.getRegisteredUsers();
		ArrayList<HashMap<String, String>> userArrayList = new ArrayList<HashMap<String, String>>();

	
		if (usertDetails.isEmpty())
			return null;
		else
			
			for (int x=0; x<usertDetails.size(); x++) {
				  HashMap<String, String> hashmap = new HashMap<String, String>();
		  	   
		  	        hashmap.put("userName", usertDetails.get(x).getUserName());
		  	        hashmap.put("role",usertDetails.get(x).getRoles().getRoleName());
		  	      hashmap.put("authorized",usertDetails.get(x).getAuthorized());
		  	    hashmap.put("emailId",usertDetails.get(x).getEmailId());
		  
		  	  userArrayList.add(x, hashmap);
			}
			
			
			
			return new ResponseEntity(userArrayList, HttpStatus.OK);
		
	
	}
	@PostMapping("/getUserType")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public @ResponseBody ResponseEntity <List<UserRegisteration>> getAuthorized(@RequestBody String authorized) {
		/*userRegService.getUserVerification(userName, password);
		UserRegisteration user = userRegService.findByUsername(userName);*/
		//UserRegisteration user = userRegService.findByUsername(loginDto.getUserName());
		List<UserRegisteration> usertDetails = userRegService.getUserType(authorized);
		 return new ResponseEntity(usertDetails, HttpStatus.OK);
	}
	
	
	@PutMapping("/approveUser")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity<String> approveUser(@RequestBody LoginDto userDetails)
		 {
	 userRegService.approveUser(userDetails);
	 return new ResponseEntity(HttpStatus.OK);
	
	}
	@PutMapping("/roleChange/{userName}/{roleId}")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity<String> roleChange(@PathVariable String userName, @PathVariable int roleId )
	{
		userRegService.changeRole(userName, roleId);
		
	 return new ResponseEntity(HttpStatus.OK);
	
	}

}
