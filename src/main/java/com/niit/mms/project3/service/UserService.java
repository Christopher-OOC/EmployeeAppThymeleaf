package com.niit.mms.project3.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.niit.mms.project3.dto.UserRegistrationDto;
import com.niit.mms.project3.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
