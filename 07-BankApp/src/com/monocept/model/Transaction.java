package com.monocept.model;

public class Transaction {
	private String username;
	private int accountno;
	private double amount;
	private String transactiontype;

	public Transaction(String username, int accountno, double amount, String transactiontype) {
		super();
		this.username = username;
		this.accountno = accountno;
		this.amount = amount;
		this.transactiontype = transactiontype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccountno() {
		return accountno;
	}

	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	@Override
	public String toString() {
		return "Transaction [username=" + username + ", accountno=" + accountno + ", amount=" + amount
				+ ", transactiontype=" + transactiontype + "]";
	}

}