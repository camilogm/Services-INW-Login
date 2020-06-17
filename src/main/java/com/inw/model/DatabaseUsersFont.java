package com.inw.model;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.inw.model.GetUser;

import sv.hawklibrary.com.ORM.ORMApplicationTables;
import sv.hawklibrary.com.validators.NotFoundException;


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
				user.setRol("consumidor");
			}
			else if (user.getRolId()==2)
				user.setRol("transportista");
			else if (user.getRolId()==3)
				user.setRol("vendedor");
			
			
			return user;
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}catch (NotFoundException ex) {
			
		}
		return null;	
	}

	
	
}
