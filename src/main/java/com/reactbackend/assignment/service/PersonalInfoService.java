package com.reactbackend.assignment.service;

import java.util.List;

import com.reactbackend.assignment.model.Personalinformation;
import com.reactbackend.assignment.model.UserRegisteration;

public interface PersonalInfoService {
	void savePersonalInfo(Personalinformation personalInfo);
	public List<Personalinformation> getAllUserList();
	Personalinformation getPersonalInfoByUser(UserRegisteration user);
	

}
