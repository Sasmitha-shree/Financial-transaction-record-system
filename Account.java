package com.wipro.ftrs.entity;

public class Account {
	private String accountId;
	private String accountHolderName;
	private double balance;
	public Account(String accountId, String accountHolderName, double balance) {
		super();
		this.accountId = accountId;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
