package com.inw.util;

import java.util.ArrayList;
import java.util.Collection;

import com.inw.model.GetUser;
import com.inw.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsAuthentication implements UserDetails,  CredentialsContainer  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName="";
	private User user=null;
	
	@Qualifier("ArrayUsersFont")
	@Autowired
	private GetUser getUser;
	
	public UserDetailsAuthentication() { 
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> list=new ArrayList<>();
		list.add(new SimpleGrantedAuthority("ADMIN"));
		return list;
	}

	@Override
	public String getPassword() {
		
		this.setUser();
		return user!=null ? user.getPassword() : null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void eraseCredentials() {
		
	}
	
	public void setUserName(String userName) {
		this.userName=userName;	
	}
	
	private void setUser() {
		if (user==null) {
			user=this.getUser.execute(this.userName);
		}
	}
	public User getUser() {
		return this.user;
	}

}
