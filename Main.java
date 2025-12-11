package com.wipro.ftrs.main;

import java.util.ArrayList;
import com.wipro.ftrs.entity.*;
import com.wipro.ftrs.service.FinancialTransactionService;
import com.wipro.ftrs.util.*;

public class Main {
	public static void main(String[] args) {
		ArrayList<Account> accounts = new ArrayList<>();
		accounts.add(new Account("A001", "Vishal Kumar", 5000));
		accounts.add(new Account("A002", "Neha Sharma", 7000));
		ArrayList<TransactionRecord> transactions = new ArrayList<>();
		FinancialTransactionService service = new FinancialTransactionService(accounts, transactions);
		try {
		// Deposit
		service.deposit("A001", 1500, "Salary credit");
		// Withdraw
		service.withdraw("A001", 500, "ATM withdrawal");
		// Transfer
		service.transfer("A001", "A002", 2000, "Online transfer to Neha");
		// History
		System.out.println("--- Transaction History for A001 ---");
		for (TransactionRecord tr : service.getTransactionHistory("A001")) {
		System.out.println(tr.getType() + " - " + tr.getAmount());
		}
		// Summary
		System.out.println("\n--- Account Summary ---");
		System.out.println(service.generateAccountSummary("A001"));
		} catch (Exception ex) {
		System.out.println(ex);
		}
		}

}
