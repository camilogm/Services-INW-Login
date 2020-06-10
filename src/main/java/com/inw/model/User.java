package com.inw.model;

public class User {

	
	private String email;
	private String password;
	private String name;
	private String lastName;
	private String rol;
	
	
	public User() {
		
	}

	public User(String email, String password, String name, String lastName, String rol) {
		
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
