package com.stackroute.capstone.userauthservice.services;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.capstone.userauthservice.exception.UserAlreadyExistsException;
import com.stackroute.capstone.userauthservice.exception.UserNotFoundException;
import com.stackroute.capstone.userauthservice.model.User;
import com.stackroute.capstone.userauthservice.repository.UserRepository;



public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	private User user;

	@InjectMocks
	private UserServiceImpl service;

	private Optional<User> options;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User(1,"hii", "Chandan", "Mishra", "123456", new Date());
		options = Optional.of(user);
	}

	@Test
	public void testSaveUserSuccess() throws UserAlreadyExistsException {
		when(userRepository.save(user)).thenReturn(user);
		boolean flag = service.saveUser(user);
		String s1=String.valueOf(true);
		String s2=String.valueOf(flag);
		assertEquals(s1, s2,"Cannot Register User");
		verify(userRepository, times(1)).save(user);
	}


	@Test
	public void testValidateSuccess() throws UserNotFoundException {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = service.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals(user.getUserId(), userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

}
