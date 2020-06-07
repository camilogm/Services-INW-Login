package com.inw.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SignInModel {

	
	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String name;
	@NotNull
	private String lastName;
	@NotNull
	private String role;
	public SignInModel() {
		
	}
	public SignInModel(@NotNull String email, @NotNull String password, @NotNull String name, @NotNull String lastName,
			@NotNull String role) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getLastName() {
		return lastName;
	}
	public String getRole() {
		return role;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
