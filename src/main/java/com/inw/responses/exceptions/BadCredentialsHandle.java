package com.inw.responses.exceptions;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadCredentialsHandle  {
	
	@Autowired
	@Qualifier("ErrorClass1")
	private Error error;
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handle(BadCredentialsException ex){
		
		HttpStatus status=HttpStatus.BAD_REQUEST;
    	error.setError(status.value(), "Usuario o contraseña incorrectos");
        return new ResponseEntity<>(error.getMessage(),HttpStatus.BAD_REQUEST);	
	}
}
