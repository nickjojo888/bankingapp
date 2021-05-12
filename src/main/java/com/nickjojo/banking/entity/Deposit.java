package com.nickjojo.banking.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "deposits")
public class Deposit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(referencedColumnName = "accountNumber")
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	@Transient
	private String accountNumber;

	@Column(name = "amount")
	private double amount;

	@Column(name = "date")
	private Date date;

	public Deposit() {

	}

	public Deposit(User user, String accountNumber, double amount, Date date) {
		this.user = user;
		this.accountNumber = accountNumber;
		this.amount = amount;
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
