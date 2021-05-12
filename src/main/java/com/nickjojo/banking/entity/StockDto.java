package com.nickjojo.banking.entity;

import java.math.BigDecimal;

public class StockDto {

	private String symbol;
	private String name;
	private BigDecimal price;
	private BigDecimal change;
	private String currency;
	private BigDecimal bid;
	private BigDecimal ask;
	private BigDecimal dividend;
	private BigDecimal openPrice;

	public StockDto() {

	}

	public StockDto(String symbol, String name, BigDecimal price, BigDecimal change, BigDecimal dividend,
			BigDecimal openPrice) {
		this.symbol = symbol;
		this.name = name;
		this.price = price;
		this.change = change;
		this.dividend = dividend;
		this.openPrice = openPrice;
	}

	public StockDto(String symbol, String name, BigDecimal price, BigDecimal ask, BigDecimal bid, BigDecimal dividend,
			BigDecimal change) {
		this.symbol = symbol;
		this.name = name;
		this.ask = ask;
		this.bid = bid;
		this.price = price;
		this.change = change;
		this.dividend = dividend;
	}

	public StockDto(String name, BigDecimal price, BigDecimal change, String currency, BigDecimal bid) {
		super();
		this.name = name;
		this.price = price;
		this.change = change;
		this.currency = currency;
		this.bid = bid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getDividend() {
		return dividend;
	}

	public void setDividend(BigDecimal dividend) {
		this.dividend = dividend;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getAsk() {
		return ask;
	}

	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}

	@Override
	public String toString() {
		return "Stock Name: " + this.getName() + "\nStock Price: " + this.getPrice() + "\nChange: " + this.getChange()
				+ "\nStock Currency: " + this.getCurrency();

	}

}
