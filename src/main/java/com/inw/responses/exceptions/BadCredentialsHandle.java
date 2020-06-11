package com.inw.responses.exceptions;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inw.DTO.Response;

@RestControllerAdvice
@Order(1)
public class BadCredentialsHandle  {
	
	@Autowired
	@Qualifier("ErrorClass1")
	private Error error;
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handle(BadCredentialsException ex){
		
    	error.setError("Usuario o contraseña incorrectos",null);
        return new ResponseEntity<>(
        		Response.bad_request(error)
        		,HttpStatus.BAD_REQUEST);	
	}
}
