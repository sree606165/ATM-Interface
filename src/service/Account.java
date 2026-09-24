package service;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String accountNumber;
    private String name;
    private String pin;
    private double balance;

    private List<Transaction> transactions;

    public Account(String accountNumber,
                   String name,
                   String pin,
                   double initialBalance) {

        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = initialBalance;

        transactions = new ArrayList<>();

        transactions.add(
                new Transaction(
                        "ACCOUNT CREATED",
                        initialBalance,
                        balance,
                        "Initial balance"
                )
        );
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public boolean deposit(double amount) {

        if (amount <= 0) {
            return false;
        }

        balance += amount;

        transactions.add(
                new Transaction(
                        "DEPOSIT",
                        amount,
                        balance,
                        "Cash deposited"
                )
        );

        return true;
    }

    public boolean withdraw(double amount) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;

        transactions.add(
                new Transaction(
                        "WITHDRAWAL",
                        amount,
                        balance,
                        "Cash withdrawn"
                )
        );

        return true;
    }

    public boolean transfer(double amount, Account receiver) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;

        receiver.balance += amount;

        transactions.add(
                new Transaction(
                        "TRANSFER",
                        amount,
                        balance,
                        "Transferred to "
                                + receiver.accountNumber
                )
        );

        receiver.transactions.add(
                new Transaction(
                        "TRANSFER RECEIVED",
                        amount,
                        receiver.balance,
                        "Received from "
                                + accountNumber
                )
        );

        return true;
    }

    public void displayTransactionHistory() {

        if (transactions.isEmpty()) {

            System.out.println("No transactions available.");

            return;
        }

        System.out.println("\n========== TRANSACTION HISTORY ==========");

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        System.out.println("=========================================");
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}