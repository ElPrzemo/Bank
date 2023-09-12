package org.account.model;

import org.account.menagment.TransactionMenager;
import org.exceptions.OperationNotAllowed;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {

    private UUID accountNumber;
    private BigDecimal balance;
    private List<Transaction> transactionHistory;
    private TransactionMenager transactionManager;

    public Account(int accountCounter) {
        this.accountNumber = UUID.randomUUID();
        balance = BigDecimal.ZERO;
        transactionHistory = new ArrayList<>();
        transactionManager = new TransactionMenager();
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return balance;
    }

    public void setAccountBalance(BigDecimal newBalance) {
        balance = newBalance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public TransactionMenager getTransactionManager() {
        return transactionManager;
    }

    public BigDecimal withdraw(BigDecimal amount) throws OperationNotAllowed {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            if (balance.compareTo(amount) >= 0) {
                balance = balance.subtract(amount);
                Transaction withdrawalTransaction = new Transaction(transactionHistory.size() + 1, TransactionType.WITHDRAWAL, amount);
                transactionHistory.add(withdrawalTransaction);
                return amount;
            } else {
                throw new OperationNotAllowed("Brak wystarczających środków na koncie.");
            }
        } else {
            throw new OperationNotAllowed("Nieprawidłowa kwota wypłaty.");
        }
    }

    public BigDecimal deposit(BigDecimal amount) throws OperationNotAllowed {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(amount);
            Transaction depositTransaction = new Transaction(transactionHistory.size() + 1, TransactionType.DEPOSIT, amount);
            transactionHistory.add(depositTransaction);
            return balance;
        } else {
            throw new OperationNotAllowed("Nieprawidłowa kwota wpłaty.");
        }
    }
}