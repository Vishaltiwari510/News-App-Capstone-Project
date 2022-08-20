package com.stackroute.capstone.userauthservice.controller;






import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.capstone.userauthservice.model.User;
import com.stackroute.capstone.userauthservice.services.SecurityTokenGenerator;
import com.stackroute.capstone.userauthservice.services.UserService;



@RestController
@RequestMapping("/api/v1/userservice")

public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityTokenGenerator tokenGenerator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			
			
			userService.saveUser(user);
			return new ResponseEntity<String>("User registered successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginDetail) {

		try {

			if (null == loginDetail.getUserId() || null == loginDetail.getPassword()) {
				throw new Exception("User Id or Password canot be empty.");
			}
			User user = userService.findByUserIdAndPassword(loginDetail.getUserId(), loginDetail.getPassword());
			Map<String, String> map = tokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	@PostMapping("/isAuthenticated")
	public ResponseEntity<?> isAuthenticated(HttpServletRequest request) {

		

			final String authHeader = request.getHeader("authorization");
			final String token = authHeader.substring(7);

			Map<String, Boolean> map = tokenGenerator.validateToken(token);
			return new ResponseEntity<Map<String, Boolean>>(map, HttpStatus.OK);

		
	}

}


