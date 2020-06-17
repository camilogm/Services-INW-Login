package com.inw.responses.exceptions;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inw.DTO.Response;

import sv.hawklibrary.com.validators.NotFoundException;

@RestControllerAdvice
@Order(1)
public class NotFoundHandle  {
	
	@Autowired
	@Qualifier("ErrorClass1")
	private Error error;
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handle(NotFoundException ex){
		
    	error.setError("Usuario o contraseña incorrectos",null);
        return new ResponseEntity<>(
        		Response.bad_request(error)
        		,HttpStatus.BAD_REQUEST);	
	}
}
