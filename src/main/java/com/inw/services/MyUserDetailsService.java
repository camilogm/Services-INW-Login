package com.inw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.inw.util.UserDetailsAuthentication;

@Service
public class MyUserDetailsService  implements UserDetailsService {

	
	@Autowired
	private UserDetailsAuthentication userLogIn;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		userLogIn.setUserName(userName);
		return userLogIn;
	}
	

}
