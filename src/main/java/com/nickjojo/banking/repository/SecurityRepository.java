package com.nickjojo.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nickjojo.banking.entity.Security;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Long> {

	@Query(value = "SELECT * FROM securities WHERE user_accountNumber = ?1", nativeQuery = true)
	List<Security> findAllByUser_AccountNumber(String accountNumber);
}
