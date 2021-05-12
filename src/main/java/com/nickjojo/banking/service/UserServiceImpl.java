package com.nickjojo.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickjojo.banking.entity.User;
import com.nickjojo.banking.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByResetToken(String resetToken) {
		return userRepository.findUserByResetToken(resetToken);
	}

	@Override
	public Optional<User> findUserByAccountNumber(String accountNumber) {
		return userRepository.findUserByAccountNumber(accountNumber);
	}

	@Override
	public List<User> findAllByEmail(String email) {
		return userRepository.findAllByEmail(email);
	}

	@Override
	public User findByAccountNumber(String accountNumber) {
		return userRepository.findByAccountNumber(accountNumber);
	}

}
