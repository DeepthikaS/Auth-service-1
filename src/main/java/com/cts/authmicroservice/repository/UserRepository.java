package com.cts.authmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.authmicroservice.model.Employee;

public interface UserRepository extends JpaRepository<Employee, String>{
	
	//to find a user by its user name
	public Employee findByName(String username);
}

