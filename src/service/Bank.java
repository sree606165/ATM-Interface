package service;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accounts;

    public Bank() {

        accounts = new ArrayList<>();


        accounts.add(
                new Account(
                        "1001",
                        "Sree Manikandan",
                        "1234",
                        10000
                )
        );

        accounts.add(
                new Account(
                        "0000",
                        "Sanjay M",
                        "6789",
                        10000000
                )
        );

        accounts.add(
                new Account(
                        "1003",
                        "Arun",
                        "4321",
                        20000
                )
        );
    }

    public Account findAccount(String accountNumber) {

        for (Account account : accounts) {

            if (account.getAccountNumber()
                    .equals(accountNumber)) {

                return account;
            }
        }

        return null;
    }

    public boolean accountExists(String accountNumber) {

        return findAccount(accountNumber) != null;
    }
}