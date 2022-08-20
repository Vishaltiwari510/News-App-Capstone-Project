package com.stackroute.capstone.userauthservice.services;

import java.util.Map;


import com.stackroute.capstone.userauthservice.model.User;




public interface SecurityTokenGenerator {

	Map<String, String> generateToken(User user);
	
	Map<String, Boolean> validateToken(String token);
}
