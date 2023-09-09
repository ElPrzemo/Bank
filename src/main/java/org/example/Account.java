package org.example;

public class Account {

    private static long accountNumbers =0;

    private long number;

    private long balance;
    public Account() {
        accountNumbers++;
        number = accountNumbers;
        this.balance=0;

    }

    public boolean increaseBalance(long cash) {
        if(cash > 0) {
            this.balance += cash;
            return true;
        }
        return false;
    }


    public boolean reduceBalance(long cash) {
        if(cash > 0 && balance > cash) {
            balance -= cash;
            return true;
        }

        return false;
    }



    public long getAccountNumber() {
        return number;
    }

    public long getAccountBalance() {

        return balance;
    }
}

