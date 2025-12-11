package com.wipro.ftrs.service;

import java.util.ArrayList;
import java.util.Date;

import com.wipro.ftrs.entity.Account;
import com.wipro.ftrs.entity.TransactionRecord;
import com.wipro.ftrs.util.AccountNotFoundException;
import com.wipro.ftrs.util.InsufficientBalanceException;
import com.wipro.ftrs.util.InvalidTransactionException;

public class FinancialTransactionService {

    private ArrayList<Account> accounts;
    private ArrayList<TransactionRecord> transactions;

    public FinancialTransactionService(ArrayList<Account> accounts, ArrayList<TransactionRecord> transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }

    public void addAccount(Account acc) {
        for (Account a : accounts) {
            if (a.getAccountId().equals(acc.getAccountId())) {
                return;
            }
        }
        accounts.add(acc);
    }

    public Account findAccount(String accountId) throws AccountNotFoundException {
        for (Account acc : accounts) {
            if (acc.getAccountId().equals(accountId)) {
                return acc;
            }
        }
        throw new AccountNotFoundException("Account not found: " + accountId);
    }

    public void deposit(String accountId, double amount, String remarks)
            throws AccountNotFoundException, InvalidTransactionException {

        if (amount <= 0) {
            throw new InvalidTransactionException("Amount must be positive.");
        }

        Account acc = findAccount(accountId);
        acc.setBalance(acc.getBalance() + amount);

        transactions.add(new TransactionRecord(
                "T" + (transactions.size() + 1),
                accountId,
                "DEPOSIT",
                amount,
                new Date().toString(),
                remarks
        ));
    }

    public void withdraw(String accountId, double amount, String remarks)
            throws AccountNotFoundException, InvalidTransactionException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidTransactionException("Amount must be positive.");
        }

        Account acc = findAccount(accountId);

        if (acc.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }

        acc.setBalance(acc.getBalance() - amount);

        transactions.add(new TransactionRecord(
                "T" + (transactions.size() + 1),
                accountId,
                "WITHDRAW",
                amount,
                new Date().toString(),
                remarks
        ));
    }

    public void transfer(String fromAccountId, String toAccountId, double amount, String remarks)
            throws AccountNotFoundException, InvalidTransactionException, InsufficientBalanceException {

        if (amount <= 0) {
            throw new InvalidTransactionException("Amount must be positive.");
        }

        Account from = findAccount(fromAccountId);
        Account to = findAccount(toAccountId);

        if (from.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance for transfer.");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        transactions.add(new TransactionRecord(
                "T" + (transactions.size() + 1),
                fromAccountId,
                "TRANSFER",
                amount,
                new Date().toString(),
                remarks
        ));

        transactions.add(new TransactionRecord(
                "T" + (transactions.size() + 1),
                toAccountId,
                "TRANSFER",
                amount,
                new Date().toString(),
                "Received: " + remarks
        ));
    }

    public ArrayList<TransactionRecord> getTransactionHistory(String accountId)
            throws AccountNotFoundException {

        findAccount(accountId);

        ArrayList<TransactionRecord> history = new ArrayList<>();

        for (TransactionRecord tr : transactions) {
            if (tr.getAccountId().equals(accountId)) {
                history.add(tr);
            }
        }
        return history;
    }

    public double calculateBalance(String accountId) throws AccountNotFoundException {
        Account acc = findAccount(accountId);
        return acc.getBalance();
    }

    public String generateAccountSummary(String accountId)
            throws AccountNotFoundException {

        Account acc = findAccount(accountId);
        ArrayList<TransactionRecord> history = getTransactionHistory(accountId);

        String lastTransaction = (history.size() == 0)
                ? "No transactions"
                : history.get(history.size() - 1).getType() + " - " +
                  history.get(history.size() - 1).getAmount();

        return "Account Summary\n" +
                "Account Holder: " + acc.getAccountHolderName() + "\n" +
                "Account ID: " + acc.getAccountId() + "\n" +
                "Current Balance: " + acc.getBalance() + "\n" +
                "Total Transactions: " + history.size() + "\n" +
                "Last Transaction: " + lastTransaction;
    }
}
