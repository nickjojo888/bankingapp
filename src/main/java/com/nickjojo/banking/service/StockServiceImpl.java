package com.nickjojo.banking.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nickjojo.banking.entity.StockDto;
import com.nickjojo.banking.entity.User;
import com.nickjojo.banking.entity.UserStockTicker;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private UserStocksService userStocksService;

	@Override
	public Stock getStock(String stockName) throws IOException {
		Stock stock = YahooFinance.get(stockName);
		return stock;
	}

	@Override
	public StockDto getAll() throws IOException {
//		List<Stock> stocks = YahooFinance.
		return null;
	}

	@Override
	public Map<String, Stock> getStock(String[] s) throws IOException {
		Map<String, Stock> stocks = YahooFinance.get(s);
		return stocks;
	}

	// if principal call doesnt work - since method call isnt initiated by user,
	// then try pass it as a parameter
	@Override
	public List<Stock> getDefaultStocks(User user) throws IOException {

		List<UserStockTicker> userStocks = userStocksService.findAllByUser_AccountNumber(user.getAccountNumber());
		List<String> stocks = new ArrayList<String>();
		for (int i = 0; i < userStocks.size(); i++) {
			stocks.add(userStocks.get(i).getTicker());
		}
		String[] stocksString = (String[]) stocks.toArray(new String[stocks.size()]);
		Map<String, Stock> stockMap = YahooFinance.get(stocksString);

		List<Stock> stockList = new ArrayList<Stock>();
		for (String s : stocks) {
			stockList.add(stockMap.get(s));
		}
		return stockList;
	}

}
