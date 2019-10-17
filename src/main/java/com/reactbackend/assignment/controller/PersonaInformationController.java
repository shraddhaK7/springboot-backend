package com.reactbackend.assignment.controller;

import java.util.ArrayList;

import javax.validation.Valid;

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
import com.reactbackend.assignment.model.Personalinformation;
import com.reactbackend.assignment.model.UserRegisteration;
import com.reactbackend.assignment.repository.UserRegisterationRepository;
import com.reactbackend.assignment.service.PersonalInfoServiceImpl;
import com.reactbackend.assignment.service.UserRegisterationServiceImpl;

@RestController 
public class PersonaInformationController {
	
	@Autowired
	private PersonalInfoServiceImpl personalService;
	@Autowired
	private UserRegisterationServiceImpl userRegService;
	
	@Autowired
	private UserRegisterationRepository userRegiRepository;
	
	@PostMapping("/CreatePersonal/{userName}")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity createPersonalInfo(@RequestBody Personalinformation perosnalInfo, @PathVariable String userName) {
		/*userRegService.getUserVerification(userName, password);
		*/
		//UserRegisteration user = userRegService.findByUsername(loginDto.getUserName());
		UserRegisteration user = userRegService.findByUsername(userName);
		if(user != null) {
			if(user.getRoles().getRoleName() =="access") {
				user.setAuthorized("No");
			}else{
				user.setAuthorized("Yes");
			}
			perosnalInfo.setUser(user);
			personalService.savePersonalInfo(perosnalInfo);
			return  new ResponseEntity("User Information Created Successfully", HttpStatus.CREATED);
		}else {
			return  new ResponseEntity("User is not registred in system", HttpStatus.FORBIDDEN);
		}
		
		
		
	}
	@GetMapping("/UserList")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity getUserListByRole() {
		//personalService.getAllUserList();
		
		ArrayList<Personalinformation> personalUsertDetails = (ArrayList<Personalinformation>) personalService.getAllUserList();
		if (personalUsertDetails.isEmpty())
			return new ResponseEntity("Not Available", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity(personalUsertDetails, HttpStatus.OK);
		
		
		
	}
	@GetMapping("/getByRegistredUserName/{userName}")
	@CrossOrigin(origins ={"http://localhost:3000"})
	public ResponseEntity getByRegistredUserName(@PathVariable String userName) {
		UserRegisteration user = userRegiRepository.findByUserName(userName);
	    
		Personalinformation peronsalInfo  = personalService.getPersonalInfoByUser(user);
		
		if (peronsalInfo != null)
			return new ResponseEntity(peronsalInfo, HttpStatus.OK);
		else
			return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
		
		
		
	}
	
	@PutMapping("/personalInformation/{userName}")
	@CrossOrigin(origins ={"http://localhost:3000"})
	   public ResponseEntity<String> updateUser( @PathVariable String userName,@Valid @RequestBody Personalinformation personalInformation) {
		UserRegisteration user = userRegiRepository.findByUserName(userName);
	       Personalinformation personalInformation1 = personalService.getPersonalInfoByUser(user);
	       if (user != null && personalInformation1==null) {
	           personalInformation.setUser(user);
	           personalService.savePersonalInfo(personalInformation);
	           return new ResponseEntity<String>("Personal Information Added", HttpStatus.OK);
	       }else  if(user !=null && personalInformation1!=null){
	           personalInformation1.setBirthStatus(personalInformation.getBirthStatus());
	           personalInformation1.setAddress(personalInformation.getAddress());
	           personalInformation1.setCity(personalInformation.getCity());
	           personalInformation1.setEduStatus(personalInformation.getEduStatus());
	           personalInformation1.setFirstName(personalInformation.getFirstName());
	           personalInformation1.setLastName(personalInformation.getLastName());
	           
	           personalInformation1.setMiddleName(personalInformation.getMiddleName());
	           personalInformation1.setPhone(personalInformation.getPhone());
	           personalInformation1.setMobile(personalInformation.getMobile());
	           personalInformation1.setDob(personalInformation.getDob());
	           personalInformation1.setMaritalstatus(personalInformation.getMaritalstatus());
	           personalInformation1.setPhysical(personalInformation.getPhysical());
	           personalInformation1.setState(personalInformation.getState());
	           
	           personalService.savePersonalInfo(personalInformation1);
	           return new ResponseEntity<String>("Personal Information updated.", HttpStatus.OK);
	       }
	       return new ResponseEntity<String>("Personal Information Added", HttpStatus.OK);
	   }

}
