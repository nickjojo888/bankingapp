package com.nickjojo.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

 import com.nickjojo.banking.entity.Withdraw;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long>{


	@Query(value = "SELECT * FROM withdraw WHERE user_accountNumber = ?1", nativeQuery = true)
	List<Withdraw> findAllByUser_AccountNumber(String accountNumber);
}
