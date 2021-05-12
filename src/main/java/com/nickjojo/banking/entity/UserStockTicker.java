package com.nickjojo.banking.entity;

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
@Table(name = "user_stocks")
public class UserStockTicker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@JoinColumn(referencedColumnName = "accountNumber")
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;

	@Column(name = "ticker")
	private String ticker;

	public UserStockTicker() {

	}

	public UserStockTicker(User user, String ticker) {
		super();
		this.user = user;
		this.ticker = ticker;
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

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

}
