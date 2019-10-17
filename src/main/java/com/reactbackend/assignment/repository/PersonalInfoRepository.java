package com.reactbackend.assignment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.reactbackend.assignment.model.Personalinformation;
import com.reactbackend.assignment.model.UserRegisteration;


public interface PersonalInfoRepository  extends CrudRepository<Personalinformation, Integer>{
	Personalinformation getByUser(UserRegisteration user);
}
