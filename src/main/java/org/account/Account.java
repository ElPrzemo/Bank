package org.account;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {

    private UUID accountNumber;
    private BigDecimal balance;

    public Account() {
        accountNumber = UUID.randomUUID(); // Generowanie unikalnego identyfikatora UUID
        balance = BigDecimal.ZERO; // Początkowe saldo wynosi 0
    }

    public boolean increaseBalance(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(amount);
            return true;
        }
        return false;
    }

    public boolean reduceBalance(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            return true;
        }
        return false;
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return balance;
    }
}




