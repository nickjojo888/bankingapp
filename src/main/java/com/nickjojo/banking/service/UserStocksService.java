package com.nickjojo.banking.service;

import java.util.List;

import com.nickjojo.banking.entity.UserStockTicker;

public interface UserStocksService {
	public void remove(UserStockTicker userStockTicker);

	List<UserStockTicker> findAllByUser_AccountNumber(String accountNumber);

	void save(UserStockTicker userStockTicker);
}
