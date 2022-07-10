package com.cts.authmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.authmicroservice.exception.UnauthorizedException;
import com.cts.authmicroservice.model.AuthResponse;

import com.cts.authmicroservice.model.Employee;
import com.cts.authmicroservice.model.UserToken;
import com.cts.authmicroservice.service.UserServiceImpl;




@RestController
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	@PostMapping("/login")
	public ResponseEntity<UserToken> login(@RequestBody Employee user) {
	
		return new ResponseEntity<UserToken>(userServiceImpl.login(user), HttpStatus.OK);
	}
	

	@GetMapping("/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") String token) {
	
		return new ResponseEntity<AuthResponse>(userServiceImpl.getValidity(token), HttpStatus.OK);
	}
	
	
	@GetMapping("/hello")
	public String hello()
	{
		 return "hello";
	}
  
	@PostMapping("/saveemployeecredentials")
	public String saveemployee(@RequestBody Employee emp)
	{
		
		userServiceImpl.saveemp(emp);
		return "Employee saved  Successfully";
	}
	

}
