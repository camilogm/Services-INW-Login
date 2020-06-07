package com.inw.responses.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@RestControllerAdvice
@Order(1)
public class MethodArgumentNotValidExceptionHandle {
	

	@Autowired
	@Qualifier("ErrorClass1")
	private Error error;
	

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> execute(MethodArgumentNotValidException ex) {
    	
     	HttpStatus status=HttpStatus.BAD_REQUEST;
    	BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        error.setError(status.value(), "validation error");
        ProcessErrors.processFieldErrors(error,fieldErrors);
        return new ResponseEntity<>(error,status);
    }

}