package com.inw.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

    private static final String SECRET_KEY = "my-secret-token-to-change-in-production";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
    	
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(),userDetails.getAuthorities());
    }

    private String createToken(Map<String, Object> claims, String subject, 
    		Collection<? extends GrantedAuthority> authoritiesCollection) {
   	
    	final String authorities = authoritiesCollection.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
    	
    	
    	return Jwts.builder()
				.setSubject(subject)
				.claim("auth", authorities)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer("123")
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
				.compact();
	        
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    

	public  UsernamePasswordAuthenticationToken getAuthentication(String token,
			final UserDetails userDetails) {
		
		
		if (token.startsWith("Bearer ")) { 
			token=token.substring(7);
		}
		
		
		final Claims claims =  this.extractAllClaims(token);
		
		final Collection<SimpleGrantedAuthority> authorities =
				Arrays.stream(claims.get("auth").toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}
    
}
