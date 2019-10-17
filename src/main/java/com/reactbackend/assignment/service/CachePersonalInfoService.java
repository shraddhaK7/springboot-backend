package com.reactbackend.assignment.service;

import java.util.List;

import com.reactbackend.assignment.model.CachePersonalInfo;
import com.reactbackend.assignment.model.UserRegisteration;

public interface CachePersonalInfoService {
	void saveCachePersonalInfo(CachePersonalInfo cachePersonalInfo);
	List<CachePersonalInfo> getCacheRegisteredUsers();
	CachePersonalInfo findByCacheUserName(String username);
	void deleteByuserName(String userName);
	
	void deleteById(CachePersonalInfo cacheuser);
}
