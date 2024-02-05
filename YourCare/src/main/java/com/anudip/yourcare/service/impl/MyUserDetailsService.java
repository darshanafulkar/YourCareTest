package com.anudip.yourcare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.anudip.yourcare.repository.UserRepository;

import com.anudip.yourcare.entity.User;

//@Component
@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		if(user != null)
		{
			return new UserPrincipal(user);
		}
		else
		{
			throw new UsernameNotFoundException("User not found");
		}
	}

}
