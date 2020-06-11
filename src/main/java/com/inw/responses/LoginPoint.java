package com.inw.responses;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.inw.requests.LoginModel;
import com.inw.util.*;
import com.inw.services.MyUserDetailsService;
import com.inw.DTO.LoginDTO;
import com.inw.DTO.Response;
import com.inw.model.User;

@RestController
public class LoginPoint {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private UserDetailsAuthentication userDetails;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value="/login",method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseEntity<?> login( @Valid @RequestBody LoginModel authenticationRequest){
				
		userDetails.resetUser();
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
				);		
		
		
		final UserDetailsAuthentication userDetails=(UserDetailsAuthentication) userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final User user = userDetails.getUser();
		user.setPassword(null);
		final String jwt = jwtUtil.generateToken(userDetails);
		LoginDTO login = new LoginDTO(jwt,user.getRol(),user.getName(),user.getLastName());		
		return ResponseEntity.ok(
				Response.ok(login)
				);	
		
	}
		
}
