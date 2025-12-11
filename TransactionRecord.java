package com.wipro.ftrs.entity;

public class TransactionRecord {

	private String transactionId;
	private String accountId;
	private String type;
	private double amount;
	private String date;
	private String remarks;
	public TransactionRecord(String transactionId, String accountId, String type, double amount, String date,
			String remarks) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.remarks = remarks;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
