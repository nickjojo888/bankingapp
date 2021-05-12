package com.nickjojo.banking.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(referencedColumnName = "accountNumber")
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	@Column(name = "stockCode")
	private String stockCode;

	@Column(name = "type")
	private TransactionType TransactionType;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private double price;

	@Column(name = "date")
	@JsonFormat(pattern = "DD/MM/YYYY")
	private LocalDateTime date;

    

	public Transaction() {

	}

	public Transaction(User user, String stockCode,
			com.nickjojo.banking.entity.TransactionType transactionType, int quantity, double price,
			LocalDateTime date) {
		this.user = user;
		this.stockCode = stockCode;
		TransactionType = transactionType;
		this.quantity = quantity;
		this.price = price;
		this.date = date;
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

	public TransactionType getTransactionType() {
		return TransactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		TransactionType = transactionType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getFromDateString() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    return formatter.format(this.getDate());
	}

}