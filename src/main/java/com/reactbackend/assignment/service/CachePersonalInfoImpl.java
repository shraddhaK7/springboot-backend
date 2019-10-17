package com.reactbackend.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactbackend.assignment.model.CachePersonalInfo;
import com.reactbackend.assignment.model.UserRegisteration;
import com.reactbackend.assignment.repository.CachePersonalInfoRepository;


@Service
public class CachePersonalInfoImpl implements CachePersonalInfoService{
	
	@Autowired
	CachePersonalInfoRepository cachePersonalRepos; 
	
	@Override
	public void saveCachePersonalInfo(CachePersonalInfo cachePersonalInfo) {
		// TODO Auto-generated method stub
		cachePersonalRepos.save(cachePersonalInfo);
	}

	@Override
	public List<CachePersonalInfo> getCacheRegisteredUsers() {
		// TODO Auto-generated method stub
		List<CachePersonalInfo> cacheuserDetail = new ArrayList<>();
		cachePersonalRepos.findAll().forEach(cacheuserDetail::add);
		
		return cacheuserDetail;
	}

	@Override
	public CachePersonalInfo findByCacheUserName(String username) {
		// TODO Auto-generated method stub
		return cachePersonalRepos.findByuserName(username);
	}

	@Override
	public void deleteByuserName(String userName) {
		// TODO Auto-generated method stub
		cachePersonalRepos.deleteByuserName(userName);
	}

	@Override
	public void deleteById(CachePersonalInfo cacheuser) {
		// TODO Auto-generated method stub
		cachePersonalRepos.delete(cacheuser);
		
	}
	
}
