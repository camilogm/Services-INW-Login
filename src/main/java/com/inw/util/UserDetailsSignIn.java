package com.inw.util;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.inw.requests.SignInModel;

@Component
public class UserDetailsSignIn implements UserDetails,  CredentialsContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SignInModel user = null;
	
	
	
	public UserDetailsSignIn() {
		
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> list=new ArrayList<>();
		list.add(new SimpleGrantedAuthority(user.getRole()));
		return list;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getEmail();
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

	public SignInModel getUserFirstLogIn() {
		return this.user;
	}


	public void setUserFirstLogIn(SignInModel userFirstLogIn) {
		this.user = userFirstLogIn;	
	}
	

	
	
	
}
