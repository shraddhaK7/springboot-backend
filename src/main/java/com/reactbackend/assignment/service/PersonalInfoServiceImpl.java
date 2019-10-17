package com.reactbackend.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactbackend.assignment.model.Personalinformation;
import com.reactbackend.assignment.model.UserRegisteration;
import com.reactbackend.assignment.repository.PersonalInfoRepository;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {
	@Autowired
	PersonalInfoRepository personalRepos; 
	
	@Override
	public void savePersonalInfo(Personalinformation personalInfo) {
		// TODO Auto-generated method stub
		personalRepos.save(personalInfo);
	}

	@Override
	public List<Personalinformation> getAllUserList() {
		// TODO Auto-generated method stub
		List<Personalinformation> personalUserDetail = new ArrayList<>();
		personalRepos.findAll().forEach(personalUserDetail::add);
	
		return personalUserDetail;
	}

	@Override
	public Personalinformation getPersonalInfoByUser(UserRegisteration user) {
		// TODO Auto-generated method stub
		
		return personalRepos.getByUser(user);
	}

	

	
	

	
}
