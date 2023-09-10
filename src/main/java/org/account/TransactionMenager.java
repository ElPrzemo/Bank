package org.account;

import java.math.BigDecimal;

public class TransactionMenager {
    public boolean increaseBalance(Account account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            account.getBalance().add(amount);
            Transaction transaction = new Transaction(account.getTransactionHistory().size() + 1, TransactionType.DEPOSIT, amount);
            account.getTransactionHistory().add(transaction);
            return true;
        }
        return false;
    }

    public boolean reduceBalance(Account account, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && account.getBalance().compareTo(amount) >= 0) {
            account.getBalance().subtract(amount);
            Transaction transaction = new Transaction(account.getTransactionHistory().size() + 1, TransactionType.WITHDRAWAL, amount);
            account.getTransactionHistory().add(transaction);
            return true;
        }
        return false;
    }
}
}
