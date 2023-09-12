package org.account.model;

import java.math.BigDecimal;

public class Transaction {

    private int transactionNumber;
    private TransactionType transactionType;
    private BigDecimal amount;

    public Transaction(int transactionNumber, TransactionType transactionType, BigDecimal amount) {
        this.transactionNumber = transactionNumber;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    // Getters and setters (jeśli są potrzebne)

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

