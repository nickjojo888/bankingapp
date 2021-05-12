package com.nickjojo.banking.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickjojo.banking.entity.UserStockTicker;
import com.nickjojo.banking.repository.UserStocksRepository;

@Service
@Transactional
public class UserStocksServiceImpl implements UserStocksService {

	@Autowired
	private UserStocksRepository userStocksRepository;

	@Override
	public List<UserStockTicker> findAllByUser_AccountNumber(String accountNumber) {
		return userStocksRepository.findAllByUser_AccountNumber(accountNumber);
	}

	@Override
	public void remove(UserStockTicker userStockTicker) {
		userStocksRepository.remove(userStockTicker.getTicker(), userStockTicker.getUser().getAccountNumber());
	}

	@Override
	public void save(UserStockTicker userStockTicker) {
		userStocksRepository.save(userStockTicker);

	}

}
