package org.account.menagment;

import org.account.model.Account;
import org.account.model.Transaction;
import org.account.model.TransactionType;

import java.math.BigDecimal;

public class TransactionMenager {

    public void increaseBalance(Account account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal newBalance = account.getAccountBalance().add(amount);
            account.setAccountBalance(newBalance);
            account.getTransactionHistory().add(new Transaction(account.getTransactionHistory().size() + 1, TransactionType.DEPOSIT, amount));
        }
    }

    public void reduceBalance(Account account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && account.getAccountBalance().compareTo(amount) >= 0) {
            BigDecimal newBalance = account.getAccountBalance().subtract(amount);
            account.setAccountBalance(newBalance);
            account.getTransactionHistory().add(new Transaction(account.getTransactionHistory().size() + 1, TransactionType.WITHDRAWAL, amount));
        }
    }

    public void addTransaction(Account account, TransactionType type, BigDecimal amount) {
        if (type == TransactionType.DEPOSIT) {
            increaseBalance(account, amount);
        } else if (type == TransactionType.WITHDRAWAL) {
            reduceBalance(account, amount);
        }
    }
}