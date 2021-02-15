package com.filmland.springboot.securityconfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filmland.springboot.model.Customer;
import com.filmland.springboot.model.CustomerCategoryData;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {			
			Customer creds = new ObjectMapper().readValue(request.getInputStream(), Customer.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException("Could not read request" + e);
		}
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication authentication) {		
		String token = Jwts.builder().setSubject(((User) authentication.getPrincipal()).getUsername()).setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
				.signWith(SignatureAlgorithm.HS512, "SecretKeyToGenJWTs".getBytes()).compact();
		response.addHeader("Authorization", "Bearer " + token);
		
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", "Login Successful");
		body.put("message", "Welcome " + ((User) authentication.getPrincipal()).getUsername());
		
		String jsonString = new Gson().toJson(body);
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    try {
	    	response.getWriter().write(jsonString);
	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
}