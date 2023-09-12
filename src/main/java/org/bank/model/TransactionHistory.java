package org.bank.model;

import org.account.model.Account;
import org.account.model.Transaction;
import org.account.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Account account, Transaction transaction) {
        account.getTransactionHistory().add(transaction);
        transactions.add(transaction);
    }

    private boolean isTransactionWithinCriteria(Transaction transaction, LocalDate startDate, LocalDate endDate, TransactionType transactionType, BigDecimal minAmount, BigDecimal maxAmount) {
        boolean withinDateRange = (startDate == null || !transaction.getTransactionDate().toLocalDate().isBefore(startDate)) &&
                (endDate == null || !transaction.getTransactionDate().toLocalDate().isAfter(endDate));

        boolean matchesTransactionType = transactionType == null || transaction.getTransactionType() == transactionType;

        boolean withinAmountRange = (minAmount == null || transaction.getAmount().compareTo(minAmount) >= 0) &&
                (maxAmount == null || transaction.getAmount().compareTo(maxAmount) <= 0);

        return withinDateRange && matchesTransactionType && withinAmountRange;
    }

    public List<Transaction> filterTransactionsByCriteria(LocalDate startDate, LocalDate endDate, TransactionType transactionType, BigDecimal minAmount, BigDecimal maxAmount) {
        return transactions.stream()
                .filter(transaction -> isTransactionWithinCriteria(transaction, startDate, endDate, transactionType, minAmount, maxAmount))
                .collect(Collectors.toList());
    }

    public List<Transaction> generateMonthlyReport(YearMonth yearMonth) {
        return transactions.stream()
                .filter(transaction -> isTransactionInMonth(transaction, yearMonth))
                .collect(Collectors.toList());
    }

    private boolean isTransactionInMonth(Transaction transaction, YearMonth yearMonth) {
        LocalDateTime transactionDate = transaction.getTransactionDate();
        YearMonth transactionYearMonth = YearMonth.of(transactionDate.getYear(), transactionDate.getMonth());
        return transactionYearMonth.equals(yearMonth);
    }

    public Map<LocalDate, BigDecimal> generateMonthlyReport() {
        Map<LocalDate, BigDecimal> monthlyReport = new HashMap<>();

        for (Transaction transaction : transactions) {
            LocalDateTime transactionDate = transaction.getTransactionDate();
            LocalDate monthYear = LocalDate.of(transactionDate.getYear(), transactionDate.getMonth(), 1);

            BigDecimal totalAmount = monthlyReport.getOrDefault(monthYear, BigDecimal.ZERO);
            totalAmount = totalAmount.add(transaction.getAmount());

            monthlyReport.put(monthYear, totalAmount);
        }

        return monthlyReport;
    }

    // Generowanie raportu rocznego na podstawie historii transakcji
    public Map<Integer, BigDecimal> generateAnnualReport() {
        Map<Integer, BigDecimal> annualReport = new HashMap<>();

        for (Transaction transaction : transactions) {
            LocalDateTime transactionDate = transaction.getTransactionDate();
            int year = transactionDate.getYear();

            BigDecimal totalAmount = annualReport.getOrDefault(year, BigDecimal.ZERO);
            totalAmount = totalAmount.add(transaction.getAmount());

            annualReport.put(year, totalAmount);
        }

        return annualReport;
    }
}