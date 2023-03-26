package com.monocept.model;

public class Customer {
	private String username;
	private double balance;
	private int accountno;

	public Customer(String username, double balance, int accountno) {
		super();
		this.username = username;
		this.balance = balance;
		this.accountno = accountno;
	}
	

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", balance=" + balance + ", accountno=" + accountno + "]";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountno() {
		return accountno;
	}

	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}

}