package com.inw.responses.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
@Qualifier("ErrorClass1")
@Primary
public class Error {
    private int status;
    private String message;
    private List<FieldError> fieldErrors;

    public Error() {
    	
    }
    
    public Error(int status, String message) {
        this.status = status;
        this.message = message;
        this.fieldErrors=new ArrayList<>();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void addFieldError(String objectName, String message) {
        FieldError error = new FieldError(objectName, message, "");
        fieldErrors.add(error);
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

	public void setError(int status, String message) {
		 this.status = status;
	     this.message = message;
	     this.fieldErrors=new ArrayList<>();
	}
}