package com.nickjojo.banking.service;

import java.util.List;

import com.nickjojo.banking.entity.Deposit;
import com.nickjojo.banking.entity.Withdraw;

public interface WithdrawService {
	List<Withdraw> findAllByUser_AccountNumber(String accountNumber);

	void save(Withdraw withdraw);
	
}
