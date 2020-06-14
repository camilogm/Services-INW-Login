package com.inw.model;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inw.model.GetUser;

import sv.hawkframework.com.ORM.ORMApplicationTables;

@Repository
@Qualifier("DatabaseUsersFont")
public class DatabaseUsersFont implements GetUser{

	@Override
	public User execute(String email) {
		
	
		ORMApplicationTables<User> userORM = new ORMApplicationTables<>(User.class);
		Object[][] conditions = {
				{"email","=",email,null}
		};
		
		User user = userORM.find(conditions);
		
		if (user.getRolId()==1) { 
			user.setRol("ADMIN");
		}
		return user;
	}

	
	
}
