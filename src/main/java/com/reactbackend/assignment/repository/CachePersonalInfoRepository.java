package com.reactbackend.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.reactbackend.assignment.model.CachePersonalInfo;
import com.reactbackend.assignment.model.UserRegisteration;

public interface CachePersonalInfoRepository extends CrudRepository<CachePersonalInfo, Integer>{
	CachePersonalInfo findByuserName(String userName);
	void deleteByuserName(String userName);

}
