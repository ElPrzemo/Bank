package org.bank;

import org.account.Account;
import org.exceptions.AccountNotExists;
import org.exceptions.OperationNotAllowed;

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

    public long deposit(long accountNumber, long cash) throws AccountNotExists, OperationNotAllowed {
        Account account = findAccount(accountNumber);

        if (account.increaseBalance(cash)) {
            return account.getAccountBalance();
        } else {
            throw new OperationNotAllowed("Operacja niedozwolona");
        }
    }

    public boolean transfer(long sourceNumber, long destinationNumber, long cash) {

        try {
            withdraw(sourceNumber, cash);
            deposit(destinationNumber, cash);

        } catch (AccountNotExists | OperationNotAllowed e) {
            return false;
        }

        return true;
    }

    public long accountBalance(long accountNumber) throws AccountNotExists  {
        Account account = findAccount(accountNumber);
        return account.getAccountBalance();
    }

    public long bankBalance() {
        long sumBalance =0;

        for (Account account: accounts){
            sumBalance+=account.getAccountBalance();
        }
        return sumBalance;
    }
}

