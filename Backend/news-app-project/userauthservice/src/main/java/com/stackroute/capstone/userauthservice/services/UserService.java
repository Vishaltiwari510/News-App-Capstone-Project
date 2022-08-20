package com.stackroute.capstone.userauthservice.services;

import com.stackroute.capstone.userauthservice.exception.UserAlreadyExistsException;
import com.stackroute.capstone.userauthservice.exception.UserNotFoundException;
import com.stackroute.capstone.userauthservice.model.User;

public interface UserService {

	boolean saveUser(User user) throws UserAlreadyExistsException;

	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
