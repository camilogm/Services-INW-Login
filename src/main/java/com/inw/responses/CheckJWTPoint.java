package com.inw.responses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inw.DTO.CheckInJWT;
import com.inw.model.User;
import com.inw.util.UserDetailsAuthentication;

@RestController
public class CheckJWTPoint {

	
	@Autowired
	private UserDetailsAuthentication userDetails;
	
	
	@RequestMapping( value = "/checkaccess" , method = RequestMethod.POST,
			headers = "Accept=application/json")
	public ResponseEntity<?> execute(){ 
		
		final User user = userDetails.getUser();
		final CheckInJWT response = new CheckInJWT(true,user);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}
