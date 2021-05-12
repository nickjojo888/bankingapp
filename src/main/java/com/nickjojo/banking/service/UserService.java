package com.nickjojo.banking.service;

import java.util.List;
import java.util.Optional;

import com.nickjojo.banking.entity.User;

public interface UserService {
	
	User findByUsername(String username);

	void save(User user);

	User findByEmail(String email);

	List<User> findAll();
	
	Optional<User> findByResetToken(String resetToken);
	
	Optional<User> findUserByAccountNumber(String accountNumber);

	List<User> findAllByEmail(String email);
	
	User findByAccountNumber(String accountNumber);
}
