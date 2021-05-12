package com.nickjojo.banking.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nickjojo.banking.service.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new MyUserDetails(user);
	}

}
