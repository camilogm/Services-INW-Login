package com.inw.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("ArrayUsersFont")
public class ArrayUsersFont implements GetUser {

	private String[] rols = {"ADMIN", "CLIENTE"};
	private String[] usersName = {"gmcamilo" , "luiszapato" };
	private String[] names = { "Cam","Lu"};
	private String[] lastNames= {"Gon", "God"};
	private String[] password  = {"12345678" , "pppp1111" };
	private String[] email = {"camilo@gmail.com","luis@yahoo.com"}; 
	private Integer[] IDs = {1,2};
	
	@Override
	public User execute(String userName) {
	
		for (int i=0;i<usersName.length;i++) {
			
			if (userName.equals(email[i])) {
				return new User(IDs[i],email[i],password[i]
						, names[i],lastNames[i], rols[i]
						);
			}			
		}
		return null;	
	}

	
}
