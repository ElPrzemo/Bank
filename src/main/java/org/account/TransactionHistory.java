package org.account;

public class TransactionHistory {
    public void addTransaction(Account account, Transaction transaction) {
        account.getTransactionHistory().add(transaction);
    }
}

