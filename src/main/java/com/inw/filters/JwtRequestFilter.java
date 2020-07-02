package com.inw.filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.inw.model.DatabaseUsersFont;
import com.inw.model.GetUser;
import com.inw.model.User;
import com.inw.responses.exceptions.Error;
import com.inw.serializable.GetStringfy;
import com.inw.serializable.GetStringfyFromGson;
import com.inw.services.MyUserDetailsService;
import com.inw.util.JwtUtil;
import com.inw.util.UserDetailsAuthentication;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;;

@Component
@Order(2)
public class JwtRequestFilter extends OncePerRequestFilter {

	  	@Autowired
	  	private MyUserDetailsService userDetailsService;
	  	@Autowired
	  	private UserDetailsAuthentication userDetails;

	  	private GetUser getUser = new DatabaseUsersFont();
	  	
	   	@Autowired
	    private JwtUtil jwtUtil;
	   	
	   	@Autowired
	   	private Error error;
	   	
	   	@Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {
	   		
		 final String authorizationHeader = request.getHeader("Authorization");
		 
		 	//allows the to don't authenticate
		 	if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/prueba")) {
	        	chain.doFilter(request, response);
	        	return;
	        }
     	
	        String userName = null;
	        String jwt = null;

	        try {
		       
	        	if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
		    
		        	jwt = authorizationHeader.substring(7);
		            userName = jwtUtil.extractUsername(jwt);
		        } 
	
	        	if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	
	 	        	
	 	        	UserDetailsAuthentication userDetails = (UserDetailsAuthentication) this.userDetailsService.loadUserByUsername(userName);
	
	 	            if (jwtUtil.validateToken(jwt, userDetails)) {
	
	 	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
	 	                		jwtUtil.getAuthentication( authorizationHeader ,userDetails);
	 	                
	 	                usernamePasswordAuthenticationToken
	 	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	 	                
	 	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	 	                
	 	                User user = getUser.execute(userName);
	 	                
	 	                if (user!=null) { 
	 	                	
	 	                	 userDetails.setUser(user);
	 	                	 chain.doFilter(request, response);	 
	 	                	 return;
	 	                	
	 	                }	 	             
	 	            }
	 	        }
	        	
	        	
	        }catch (MalformedJwtException | SignatureException ex) {
	               	
	        }      
	        
	        //if something is band, do this method an reset the return a error handle resposne
	    	this.resetResponse(response);
        	return;	 
	        	 
	 }
	   	
	 private HttpServletResponse resetResponse(HttpServletResponse response) throws IOException {
		 
		error.setError("Cannot authenticate your identity",null);
     	GetStringfy convert = new GetStringfyFromGson(); 
     	
     	response.reset();
     	response.setStatus(HttpStatus.UNAUTHORIZED.value());
     	response.setContentType("application/json");
     	response.getWriter().write(convert.execute(error, Error.class));
     	response.getWriter().flush();
     	response.getWriter().close();
     	
     	return response;
	 }
	
	 
}
