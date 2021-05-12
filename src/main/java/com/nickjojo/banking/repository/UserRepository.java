package com.nickjojo.banking.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nickjojo.banking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
	User findUserByEmail(String email);

	@Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
	User findByUsername(String username);

	@Query(value = "SELECT * FROM users WHERE reset_token = ?1", nativeQuery = true)
	Optional<User> findUserByResetToken(String resetToken);

	@Query(value = "SELECT * FROM users WHERE accountNumber = ?1", nativeQuery = true)
	Optional<User> findUserByAccountNumber(String accountNumber);

	@Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
	List<User> findAllByEmail(String email);

	@Query(value = "SELECT * fROM users WHERE accountNumber = ?1", nativeQuery = true)
	User findByAccountNumber(String accountNumber);

}
