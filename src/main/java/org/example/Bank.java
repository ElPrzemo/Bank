package org.example;

import org.example.exceptions.AccountNotExists;
import org.example.exceptions.OperationNotAllowed;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public long createAccount() {
        Account account = new Account();
        accounts.add(account);
        return account.getAccountNumber();
    }

    private Account findAccount(long accountNumber) throws AccountNotExists {
        for(Account account: accounts) {
            if(account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new AccountNotExists();
    }

    public long withdraw(long accountNumber, long cash) throws AccountNotExists, OperationNotAllowed {
        // Szukamy konta o podanym numerze
        Account account = findAccount(accountNumber);

        // Sprawdzamy, czy konto istnieje
        if (account == null) {
            throw new AccountNotExists("Konto o numerze " + accountNumber + " nie istnieje.");
        }

        // Sprawdzamy, czy operacja jest dozwolona
        if (cash <= 0) {
            throw new OperationNotAllowed("Nieprawidłowa kwota wypłaty.");
        }

        // Sprawdzamy, czy na koncie jest wystarczająco środków
        if (account.getAccountBalance() < cash) {
            throw new OperationNotAllowed("Brak wystarczających środków na koncie.");
        }

        // Wykonujemy wypłatę
        if (account.reduceBalance(cash)) {
            return cash;
        }

        // Jeśli operacja nie powiodła się, rzucamy wyjątek OperationNotAllowed
        throw new OperationNotAllowed("Nie można dokonać wypłaty.");
    }

    public long deposit(long accountNumber, long cash) {
        return 0;
    }

    public boolean transfer(long sourceNumber, long destinationNumber, long cash) {
        return true;
    }

    public long accountBalance(long accountNumber) {
        return 0;
    }

    public long bankBalance() {
        return 0;
    }
}

