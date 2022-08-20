package com.stackroute.capstone.userauthservice.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.capstone.userauthservice.exception.UserAlreadyExistsException;
import com.stackroute.capstone.userauthservice.exception.UserNotFoundException;
import com.stackroute.capstone.userauthservice.model.User;
import com.stackroute.capstone.userauthservice.repository.UserRepository;




@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;


	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> existingUser = userRepo.findByuserId(user.getUserId());
		if (existingUser.isPresent()) {
			throw new UserAlreadyExistsException("User with id already exists");
		}
		userRepo.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userRepo.findByUserIdAndPassword(userId, password);
		if (null == user) {
			throw new UserNotFoundException("UserId and Password mismatch");
		}
		return user;
	}


}

