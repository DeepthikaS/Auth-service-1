package com.cts.authmicroservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;


import com.cts.authmicroservice.model.AuthResponse;
import com.cts.authmicroservice.model.Employee;
import com.cts.authmicroservice.model.UserToken;

public interface UserService extends UserDetailsService {


	AuthResponse getValidity(String token);
    

}
