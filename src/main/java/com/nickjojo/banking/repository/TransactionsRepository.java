package com.nickjojo.banking.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nickjojo.banking.entity.Transaction;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "SELECT * FROM transactions WHERE user_accountNumber = ?1 order by date asc", nativeQuery = true)
	List<Transaction> findAllByUser_AccountNumber(String accountNumber);
	
	// Spring ORM will recognise the method name and will identify it as its own SQL query
	// given return type, parameters and the keywords used in the method name
	List<Transaction> findAllByUser_AccountNumberOrderByDateDesc(String accountNumber);
	
	@Query(value="SELECT * FROM transactions WHERE user_accountNumber = ?1 AND stockCode = ?2 order by date asc", nativeQuery=true)
	List<Transaction> findAllByUser_AccountNumberAndStockCode(String accountNumber, String symbol) ;
	
}
