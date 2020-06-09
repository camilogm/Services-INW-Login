package com.inw.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.inw.services.MyUserDetailsService;
import com.inw.util.JwtUtil;
import com.inw.util.UserDetailsAuthentication;;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	  	@Autowired
	  	private MyUserDetailsService userDetailsService;

	   	@Autowired
	    private JwtUtil jwtUtil;
	
	   	@Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {
		 
		 final String authorizationHeader = request.getHeader("Authorization");

	        String userName = null;
	        String jwt = null;

	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	    
	        	jwt = authorizationHeader.substring(7);
	            userName = jwtUtil.extractUsername(jwt);
	        } else {
	        	response.reset();
	        	response.setStatus(HttpStatus.BAD_REQUEST.value());
	        	response.getWriter().flush();
	        	return;
	        	
	        }

	        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	        	
	        	UserDetailsAuthentication userDetails = (UserDetailsAuthentication) this.userDetailsService.loadUserByUsername(userName);

	            if (jwtUtil.validateToken(jwt, userDetails)) {

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	                		jwtUtil.getAuthentication( authorizationHeader ,userDetails);
	                
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	        }
	        chain.doFilter(request, response);
		 
	 }
	
	
}
