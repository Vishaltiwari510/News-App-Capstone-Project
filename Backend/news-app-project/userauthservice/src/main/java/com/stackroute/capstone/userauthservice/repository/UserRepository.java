package com.stackroute.capstone.userauthservice.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.stackroute.capstone.userauthservice.model.User;




public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("Select user from User user where user.userId = (?1) and user.password = (?2)")
	User validate(String userId, String password);

	User findByUserIdAndPassword(String userId, String password);
	
	Optional<User> findByuserId(String userId);
}
