package com.nickjojo.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nickjojo.banking.entity.Deposit;
 
@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long>{

	@Query(value = "SELECT * FROM deposits WHERE user_accountNumber = ?1", nativeQuery = true)
	List<Deposit> findAllByUser_AccountNumber(String accountNumber);
	
}
