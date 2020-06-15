package com.inw.model;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inw.model.GetUser;

import sv.hawklibrary.com.ORM.ORMApplicationTables;


@Repository
@Qualifier("DatabaseUsersFont")
public class DatabaseUsersFont implements GetUser{

	@Override
	public User execute(String email) {
		
	
		ORMApplicationTables<User> userORM = new ORMApplicationTables<>(User.class);
		Object[][] conditions = {
				{"email","=",email,null}
		};
		
		User user;
		try {
			user = userORM.find(conditions);
			if (user.getRolId()==1) { 
				user.setRol("ADMIN");
			}
			return user;
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

	
	
}
