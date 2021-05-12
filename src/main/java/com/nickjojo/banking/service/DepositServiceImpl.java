package com.nickjojo.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickjojo.banking.entity.Deposit;
import com.nickjojo.banking.repository.DepositRepository;

@Service
public class DepositServiceImpl implements DepositService {

	@Autowired
	private DepositRepository depositRepository;

	@Override
	public void save(Deposit deposit) {
		depositRepository.save(deposit);
	}

	@Override
	public List<Deposit> findAllByUser_AccountNumber(String accountNumber) {
		return depositRepository.findAllByUser_AccountNumber(accountNumber);
	}

}
