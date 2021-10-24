package com.nickjojo.banking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nickjojo.banking.entity.Transaction;
import com.nickjojo.banking.repository.TransactionsRepository;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionsRepository transactionRepository;

	@Override
	public void save(Transaction transaction) {
		transactionRepository.save(transaction);
	}

	@Override
	public Optional<Transaction> findById(Long id) {
		return transactionRepository.findById(id);
	}

	@Override
	public List<Transaction> findAllByUser_AccountNumber(String accountNumber) {
		return transactionRepository.findAllByUser_AccountNumber(accountNumber);
	}

	@Override
	public double getVolume() {
		List<Transaction> transactions = transactionRepository.findAll();
		ChronoLocalDateTime<?> thisMonth = LocalDateTime.of(2020, 8, 1, 1, 1);
		double volume = 0;
		for (Transaction t : transactions) {
			if (t.getDate().isAfter(thisMonth)) {
				volume += t.getPrice();
			}

		}
		return volume;

	}

	@Override
	public List<Transaction> findAllByUser_AccountNumberOrderByDateDesc(String accountNumber) {
		return transactionRepository.findAllByUser_AccountNumberOrderByDateDesc(accountNumber);
	}

	@Override
	public List<Transaction> findAllByUser_AccountNumberAndStockCode(String accountNumber, String stockCode) {
		return transactionRepository.findAllByUser_AccountNumberAndStockCode(accountNumber, stockCode);
	}

}
