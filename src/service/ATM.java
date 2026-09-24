package service;

import service.Account;

public class ATM {

    private Bank bank;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public Account login(String accountNumber, String pin) {

        Account account =
                bank.findAccount(accountNumber);

        if (account != null &&
                account.validatePin(pin)) {

            return account;
        }

        return null;
    }

    public boolean deposit(
            Account account,
            double amount) {

        return account.deposit(amount);
    }

    public boolean withdraw(
            Account account,
            double amount) {

        return account.withdraw(amount);
    }

    public boolean transfer(
            Account sender,
            String receiverAccountNumber,
            double amount) {

        Account receiver =
                bank.findAccount(
                        receiverAccountNumber
                );

        if (receiver == null) {
            return false;
        }

        if (sender == receiver) {
            return false;
        }

        return sender.transfer(
                amount,
                receiver
        );
    }
}