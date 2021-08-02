package com.Spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.Spring.dto.UserRegistrationDto;
import com.Spring.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
