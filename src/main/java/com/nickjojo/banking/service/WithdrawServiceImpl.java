package com.nickjojo.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickjojo.banking.entity.Withdraw;
import com.nickjojo.banking.repository.WithdrawRepository;

@Service
public class WithdrawServiceImpl implements WithdrawService {

	@Autowired
	private WithdrawRepository withdrawRepository;
	
	@Override
	public void save(Withdraw withdraw) {
		withdrawRepository.save(withdraw);
	}

	@Override
	public List<Withdraw> findAllByUser_AccountNumber(String accountNumber) {
		return withdrawRepository.findAllByUser_AccountNumber(accountNumber);
	}

}
