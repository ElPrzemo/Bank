package org.bank.model;

import org.account.model.Account;
import org.account.model.Transaction;

public class TransactionHistory {
    public void addTransaction(Account account, Transaction transaction) {
        account.getTransactionHistory().add(transaction);
    }
}

