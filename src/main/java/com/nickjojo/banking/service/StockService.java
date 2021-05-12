package com.nickjojo.banking.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.nickjojo.banking.entity.StockDto;
import com.nickjojo.banking.entity.User;

import yahoofinance.Stock;

public interface StockService {

	Stock getStock(String stockName) throws IOException;
	
	StockDto getAll() throws IOException;
	
	Map<String, Stock> getStock(String[] s) throws IOException;
	
	List<Stock> getDefaultStocks(User user) throws IOException;

}
