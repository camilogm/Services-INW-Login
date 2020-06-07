package com.inw.responses;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inw.requests.SignInModel;
import com.inw.requests.LoginDTO;
import com.inw.services.MyUserDetailsService;
import com.inw.util.JwtUtil;
import com.inw.util.UserDetailsSignIn;

@RestController
public class SignInPoint {
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value="/signin",method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseEntity<LoginDTO> login( @Valid @RequestBody SignInModel user){
	
		final UserDetailsSignIn userDetails = (UserDetailsSignIn) userDetailsService.loadByUserName(user);
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new LoginDTO(jwt,"",user.getName(),user.getLastName()));	
		
	}

}
