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
    private int accountCounter;

    public Bank() {
        accounts = new ArrayList<>();
        accountCounter = 1;
    }

    public UUID createAccount() {
        Account account = new Account(accountCounter++);
        accounts.add(account);
        return account.getAccountNumber();
    }

    public BigDecimal withdraw(UUID accountNumber, BigDecimal cash) throws AccountNotExists, OperationNotAllowed {
        Account account = findAccount(accountNumber);
        return account != null ? account.withdraw(cash) : BigDecimal.ZERO;
    }

    public BigDecimal deposit(UUID accountNumber, BigDecimal cash) throws AccountNotExists, OperationNotAllowed {
        Account account = findAccount(accountNumber);
        return account != null ? account.deposit(cash) : BigDecimal.ZERO;
    }

    public boolean transfer(UUID sourceNumber, UUID destinationNumber, BigDecimal cash) {
        try {
            BigDecimal withdrawnAmount = withdraw(sourceNumber, cash);
            deposit(destinationNumber, withdrawnAmount);
            return true;
        } catch (AccountNotExists | OperationNotAllowed e) {
            return false;
        }
    }

    public BigDecimal accountBalance(UUID accountNumber) throws AccountNotExists {
        Account account = findAccount(accountNumber);
        return account != null ? account.getAccountBalance() : BigDecimal.ZERO;
    }

    public BigDecimal bankBalance() {
        BigDecimal sumBalance = BigDecimal.ZERO;

        for (Account account : accounts) {
            sumBalance = sumBalance.add(account.getAccountBalance());
        }
        return sumBalance;
    }

    private Account findAccount(UUID accountNumber) throws AccountNotExists {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotExists("Konto o numerze " + accountNumber + " nie istnieje.");
    }
}