package org.account;

import java.math.BigDecimal;

public class Transaction {

    private int transactionNumber;
    private BigDecimal amount;
    private TransactionType type;

    public Transaction(int transactionNumber, TransactionType type, BigDecimal amount) {
        this.transactionNumber = transactionNumber;
        this.type = type;
        this.amount = amount;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

