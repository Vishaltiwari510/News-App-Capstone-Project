package com.stackroute.capstone.userauthservice.services;
import java.util.Date;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.capstone.userauthservice.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS384, "secretkey").compact();
		Map<String, String> map = new HashMap<>();
		map.put("token", jwtToken);
		map.put("message", "User successfully logged in");
		return map;
	}
	@Override
	public Map<String, Boolean> validateToken(String token) {
		
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		Map<String, Boolean> map = new HashMap<>();
		Boolean isAuthenticated;

		if (userId != null)
			isAuthenticated = true;
		else
			isAuthenticated = false;
		

		map.put("isAuthenticated", isAuthenticated);
		return map;

	}
}
