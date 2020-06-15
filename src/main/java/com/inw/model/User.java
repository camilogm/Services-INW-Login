package com.inw.model;

import com.google.gson.annotations.SerializedName;

import sv.hawklibrary.com.ORM.Annotations.DataModelAnnotations;
import sv.hawklibrary.com.ORM.Annotations.PrimaryKey;


@DataModelAnnotations(tableName = "J0120_user")
public class User {

	@PrimaryKey
	private Integer id;
	private String email;
	private String password;
	@SerializedName("first_name")
	private String name;
	@SerializedName("last_name")
	private String lastName;
	@SerializedName("rol_id")
	private Integer rolId;
	private String rol;
	
	public User() {
		
	}

	public User(Integer id,String email, String password, String name, String lastName, String rol) {
		
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.rol = rol;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
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

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rol_id) {
		this.rolId = rol_id;
	}

	
	
}
