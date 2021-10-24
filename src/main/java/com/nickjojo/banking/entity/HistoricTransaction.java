 package com.nickjojo.banking.entity;

public class HistoricTransaction {

	private Withdraw withdraw;
	private Deposit deposit;
	private Transaction transaction;

	public HistoricTransaction(Withdraw withdraw) {
		this.withdraw = withdraw;
	}

	public HistoricTransaction(Deposit deposit) {
		this.deposit = deposit;
	}

	public HistoricTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Withdraw getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(Withdraw withdraw) {
		this.withdraw = withdraw;
	}

	public Deposit getDeposit() {
		return deposit;
	}

	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
