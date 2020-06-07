package com.inw.responses.exceptions;

import java.util.List;
import org.springframework.validation.FieldError;


public class ProcessErrors {
	
	  public static final Error processFieldErrors(Error error,List<FieldError> fieldErrors) {
		  
	        for (FieldError fieldError: fieldErrors) {        	
	            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
	        }
	        return error;
	  }
	    
}
