package com.nickjojo.banking.entity;

public enum TransactionType {

	STOCK_BUY("STOCKBUY"),
	STOCK_SELL("STOCKSELL");
	
	private String name;

	TransactionType(String name) {
		this.name = name;
	}
}
