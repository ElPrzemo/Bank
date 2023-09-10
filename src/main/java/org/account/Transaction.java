package org.account;

import java.math.BigDecimal;

public class Transaction {

        private TransactionType type;
        private BigDecimal amount;
        private String description;

        public Transaction(TransactionType type, BigDecimal amount) {
            this.type = type;
            this.amount = amount;
            this.description = type == TransactionType.DEPOSIT ? "Wpłata" : "Wypłata";
        }

        public TransactionType getType() {
            return type;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public String getDescription() {
            return description;
        }
    }

