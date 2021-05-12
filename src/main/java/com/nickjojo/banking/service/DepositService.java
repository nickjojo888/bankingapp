package com.nickjojo.banking.service;

import java.util.List;

import com.nickjojo.banking.entity.Deposit;
import com.nickjojo.banking.entity.Transaction;

public interface DepositService {
	List<Deposit> findAllByUser_AccountNumber(String accountNumber);

	void save(Deposit deposit);

}
