package com.nickjojo.banking.service;

import java.util.List;
import java.util.Optional;

import com.nickjojo.banking.entity.Transaction;

public interface TransactionService {

	double getVolume();
	
	void save(Transaction transaction);

	Optional<Transaction> findById(Long id);
	
	List<Transaction> findAllByUser_AccountNumber(String accountNumber);

	List<Transaction> findAllByUser_AccountNumberOrderByDateDesc(String accountNumber);
	
	List<Transaction> findAllByUser_AccountNumberAndStockCode(String accountNumber, String stockCode);
}
