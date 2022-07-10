package com.cts.authmicroservice.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cts.authmicroservice.exception.UnauthorizedException;
import com.cts.authmicroservice.jwt.JwtUtil;
import com.cts.authmicroservice.model.AuthResponse;

import com.cts.authmicroservice.model.Employee;
import com.cts.authmicroservice.model.UserToken;
import com.cts.authmicroservice.repository.UserRepository;




@Service


public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String userName) {
	
		Employee user = userRepository.findByName(userName);
		return new User(user.getName(), user.getPassword(), new ArrayList<>());
	}

	// authenticates the user
	public UserToken login(Employee employee) {

		final UserDetails userdetails = loadUserByUsername(employee.getName());

		UserToken userToken = new UserToken();

		// if the password matches
		if (userdetails.getPassword().equals(employee.getPassword())) {
	

			// set the values for the token
			userToken.setUsername(employee.getName());
			userToken.setEmpid(userRepository.findByName(employee.getName()).getId());
			userToken.setAuthToken(jwtUtil.generateToken(userdetails));

			return userToken;
		} else {
	
			throw new UnauthorizedException("Invalid username or password");
		}
	}


	public AuthResponse getValidity(String token) {
	
		String token1 = token.substring(7);
		AuthResponse authResponse = new AuthResponse();
		// if valid
		if (jwtUtil.validateToken(token1)) {
		

			// extract the user name
			String username = jwtUtil.extractUsername(token1);

			// set the values for the response
			authResponse.setUsername(username);
			authResponse.setValid(true);
			authResponse.setEmpid(userRepository.findByName(username).getId());
		} else {
			authResponse.setValid(false);
		
		}

		return authResponse;
	}

	public void saveemp(Employee emp) {
		
		userRepository.save(emp);
		
	}

	


	
	
}
