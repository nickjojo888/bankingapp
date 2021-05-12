package com.nickjojo.banking.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import yahoofinance.Stock;

public class StockTransfer implements Serializable {

	private static final long serialVersionUID = 7685954595695834497L;

	List<StockDto> dtoStocks = new ArrayList<StockDto>();
	

	public StockTransfer(List<StockDto> dtoStocks) {
		this.dtoStocks = dtoStocks;
	}

	public List<StockDto> getDtoStocks() {
		return dtoStocks;
	}

	public void setDtoStocks(List<StockDto> dtoStocks) {
		this.dtoStocks = dtoStocks;
	}

}
