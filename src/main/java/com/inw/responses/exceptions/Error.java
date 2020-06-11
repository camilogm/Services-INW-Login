package com.inw.responses.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import com.inw.DTO.FieldErrorDTO;

@Component
@Qualifier("ErrorClass1")
@Primary
public class Error {
    
	private String message;
    private List<FieldErrorDTO> fieldErrors;

    public Error() {
    	
    }
    
    public Error(String message, List<FieldError> fieldsError) {
        this.message = message;
        this.setFieldError(fieldsError);
    }
    
    public void  setError(String message, List<FieldError> fieldsError) {
        this.message = message;
        this.fieldErrors = fieldsError == null ? null : this.setFieldError(fieldsError);
    }
    
    public String getMessage() {
        return message;
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }
    private  List<FieldErrorDTO> setFieldError(List<FieldError> fieldsError) {
       
		List<FieldErrorDTO> fieldErrors = new ArrayList<>();
    	fieldsError.forEach(fieldError -> { 
    		
    		 String objectName = fieldError.getField();
    		 String message = fieldError.getDefaultMessage();
    		 FieldErrorDTO error = new FieldErrorDTO(objectName, message);
    	     fieldErrors.add(error);
    	});
    	return fieldErrors;
	
    }

    

}