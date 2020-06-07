package com.inw.requests;


import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class LoginModel implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Email
	private String email;
	
	@NotNull
	@Size(min=8,max = 10)
	private String password;
	
	
	
	public LoginModel(String credentialSystem, String password) {
		super();
		this.email = credentialSystem;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String credentialSystem) {
		this.email = credentialSystem;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
