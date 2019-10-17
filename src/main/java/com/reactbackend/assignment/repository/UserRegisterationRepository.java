package com.reactbackend.assignment.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.reactbackend.assignment.model.UserRegisteration;
public interface UserRegisterationRepository extends CrudRepository<UserRegisteration, Integer> {
	UserRegisteration findByUserName(String userName);

	List<UserRegisteration> findByauthorized(String authorized);
	
}
