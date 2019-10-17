package com.reactbackend.assignment.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reactbackend.assignment.model.CachePersonalInfo;
import com.reactbackend.assignment.model.Personalinformation;
import com.reactbackend.assignment.model.UserRegisteration;
import com.reactbackend.assignment.repository.UserRegisterationRepository;
import com.reactbackend.assignment.service.CachePersonalInfoImpl;
import com.reactbackend.assignment.service.PersonalInfoServiceImpl;
import com.reactbackend.assignment.service.UserRegisterationServiceImpl;
@RestController 
public class CachePersonalInfoController {
	
	@Autowired
	private CachePersonalInfoImpl cachePersonalService;
	@Autowired
	private UserRegisterationServiceImpl userRegService;
	@Autowired
	private PersonalInfoServiceImpl personalService;
	@Autowired
	private UserRegisterationRepository userRegiRepository;
	
	@PostMapping("/CreateCachePersonal/{userName}")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity cacheCreatePersonalInfo(@RequestBody CachePersonalInfo cachePerosnalInfo, @PathVariable String userName) {
		UserRegisteration user = userRegService.findByUsername(userName);
		
		if(user != null) {
			
			if(user.getEmailId()!= null)
			{
				 String emailId = user.getEmailId();
				 cachePerosnalInfo.setEmailId(emailId);
				 cachePerosnalInfo.setUserName(userName);
			}
		
			
			cachePersonalService.saveCachePersonalInfo(cachePerosnalInfo);
			return  new ResponseEntity("User Information Created Successfully", HttpStatus.CREATED);
		}else {
			return  new ResponseEntity("User is not registred in system", HttpStatus.FORBIDDEN);
		}
		
		
	}
	@GetMapping("/cacheUserList")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity getCacheUserList() {
	ArrayList<CachePersonalInfo> personalUsertDetails = (ArrayList<CachePersonalInfo>) cachePersonalService.getCacheRegisteredUsers();
		if (personalUsertDetails.isEmpty())
			return new ResponseEntity("Not Available", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity(personalUsertDetails, HttpStatus.OK);
		
		
		
	}
	@PutMapping("/editUser/{userName}")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity<String> editUser(@PathVariable String userName)
	{	
		UserRegisteration user = userRegiRepository.findByUserName(userName);
		CachePersonalInfo cacheuser = cachePersonalService.findByCacheUserName(userName);
		if(cacheuser!= null && user != null) {
			Personalinformation personalInformation1 = personalService.getPersonalInfoByUser(user);
			if(personalInformation1 != null) {
				personalInformation1.setBirthStatus(cacheuser.getBirthStatus());
			    personalInformation1.setAddress(cacheuser.getAddress());
			    personalInformation1.setCity(cacheuser.getCity());
			    personalInformation1.setEduStatus(cacheuser.getEduStatus());
			    personalInformation1.setFirstName(cacheuser.getFirstName());
			    personalInformation1.setLastName(cacheuser.getLastName());
			  
			    personalInformation1.setMiddleName(cacheuser.getMiddleName());
			    personalInformation1.setPhone(cacheuser.getPhone());
			    personalInformation1.setMobile(cacheuser.getMobile());
			    personalInformation1.setDob(cacheuser.getDob());
			    personalInformation1.setMaritalstatus(cacheuser.getMaritalstatus());
			    personalInformation1.setPhysical(cacheuser.getPhysical());
			    personalInformation1.setState(cacheuser.getState());
			 
			}
			   personalService.savePersonalInfo(personalInformation1);
	           cachePersonalService.deleteById(cacheuser);
	           return new ResponseEntity<String>("Personal Information updated.", HttpStatus.OK);
			
			
		}
		 return new ResponseEntity<String>("Personal Information Added", HttpStatus.OK);
	
	}


}
