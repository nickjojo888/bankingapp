package com.nickjojo.banking.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "securities")
public class Security implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1092665348259579929L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(referencedColumnName = "accountNumber")
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	@Column(name = "stockCode")
	private String stockCode;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "amount")
	private double amount;

	private double averagePrice;
	
	
	public Security() {

	}

	public Security(User user, String stockCode, int quantity, double amount) {
		this.user = user;
		this.stockCode = stockCode;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setAmount(BigInteger bigInteger) {
		this.amount = amount;

	}

	public double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

}