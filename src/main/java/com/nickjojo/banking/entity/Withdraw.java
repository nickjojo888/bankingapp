package com.nickjojo.banking.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "withdraw")
public class Withdraw {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(referencedColumnName = "accountNumber")
	@ManyToOne
	private User user;

	@Column(name = "toNumber")
	private String toNumber;

	@Column(name = "amount")
	private double amount;

	@Column(name = "date")
	private Date date;

	public Withdraw() {

	}

	public Withdraw(User user, String toNumber, double amount, Date date) {
		this.user = user;
		this.toNumber = toNumber;
		this.amount = amount;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getToNumber() {
		return toNumber;
	}

	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
