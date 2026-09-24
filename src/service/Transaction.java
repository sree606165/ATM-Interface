package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private double balance;
    private String description;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount,
                       double balance, String description) {

        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return dateTime.format(formatter)
                + " | "
                + type
                + " | Amount: ₹"
                + String.format("%.2f", amount)
                + " | Balance: ₹"
                + String.format("%.2f", balance)
                + " | "
                + description;
    }
}