package com.inw.responses;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.inw.requests.LoginModel;
import com.inw.util.*;
import com.inw.services.MyUserDetailsService;
import com.inw.DTO.LoginDTO;
import com.inw.model.User;

@RestController
public class LoginPoint {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value="/login",method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseEntity<LoginDTO> login( @Valid @RequestBody LoginModel authenticationRequest){
				
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
				);		
		final UserDetailsAuthentication userDetails=(UserDetailsAuthentication) userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final User user = userDetails.getUser();
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new LoginDTO(jwt,"",user.getName(),user.getLastName()));	
		
	}
	
	@RequestMapping(value="/",method = RequestMethod.GET,headers="Accept=application/json")
	@CrossOrigin(value = {"http://localhost:8080/"} )
	public ResponseEntity<?> execute(){
		return new ResponseEntity<>("prueba cors",HttpStatus.OK);
	}
	
	
}
