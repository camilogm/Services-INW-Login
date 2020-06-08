package com.inw.DTO;

import com.inw.model.User;

public class CheckInJWT {
	
	private final Boolean authenticated;
	private final User user;
	
	public CheckInJWT(Boolean authenticated, User user) {
		this.authenticated = authenticated;
		this.user = user;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public User getUser() {
		return user;
	}

}
