package com.reactbackend.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.reactbackend.assignment.model.RoleUser;

public interface RoleRespository extends CrudRepository<RoleUser, Integer> {

	}

