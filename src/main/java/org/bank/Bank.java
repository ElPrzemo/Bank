package org.bank;

import org.account.Account;
import org.exceptions.AccountNotExists;
import org.exceptions.OperationNotAllowed;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {

    private List<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public UUID createAccount() {
        Account account = new Account();
        accounts.add(account);
        return account.getAccountNumber();
    }

    private Account findAccount(UUID accountNumber) throws AccountNotExists {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotExists();
    }

    public BigDecimal withdraw(UUID accountNumber, BigDecimal cash) throws AccountNotExists, OperationNotAllowed {
        Account account = findAccount(accountNumber);

        if (account == null) {
            throw new AccountNotExists("Konto o numerze " + accountNumber + " nie istnieje.");
        }

        if (cash.compareTo(BigDecimal.ZERO) <= 0) {
            throw new OperationNotAllowed("Nieprawidłowa kwota wypłaty.");
        }

        if (account.getAccountBalance().compareTo(cash) < 0) {
            throw new OperationNotAllowed("Brak wystarczających środków na koncie.");
        }

        if (account.reduceBalance(cash)) {
            return cash;
        }

        throw new OperationNotAllowed("Nie można dokonać wypłaty.");
    }

    public BigDecimal deposit(UUID accountNumber, BigDecimal cash) throws AccountNotExists, OperationNotAllowed {
        Account account = findAccount(accountNumber);

        if (account.increaseBalance(cash)) {
            return account.getAccountBalance();
        } else {
            throw new OperationNotAllowed("Operacja niedozwolona");
        }
    }

    public boolean transfer(UUID sourceNumber, UUID destinationNumber, BigDecimal cash) {

        try {
            withdraw(sourceNumber, cash);
            deposit(destinationNumber, cash);
            return true;
        } catch (AccountNotExists | OperationNotAllowed e) {
            return false;
        }
    }

    public BigDecimal accountBalance(UUID accountNumber) throws AccountNotExists {
        Account account = findAccount(accountNumber);
        return account.getAccountBalance();
    }

    public BigDecimal bankBalance() {
        BigDecimal sumBalance = BigDecimal.ZERO;

        for (Account account : accounts) {
            sumBalance = sumBalance.add(account.getAccountBalance());
        }
        return sumBalance;
    }
}