package org.example;

import org.example.exceptions.AccountNotExists;
import org.example.exceptions.OperationNotAllowed;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    public static void main(String[] args) {

        try {
            Bank bank = new Bank();

            long accountSourceNumber = bank.createAccount();
            long accountDestinationNumber = bank.createAccount();

            System.out.println("Account source number: " + accountSourceNumber); // 1
            System.out.println("Account destination number: " + accountDestinationNumber); // 2
            System.out.println("Account source balance: " + Account.getAcc); // 3
            System.out.println("Account destination balance: "); // 4
            System.out.println("Account source deposit 10000: "); // 5
            System.out.println("Account destination deposit 30000: "); // 6
            System.out.println("Account source withdraw 2000: "); // 7
            System.out.println("Account destination withdraw 12000: "); // 8

            System.out.println("Account source transfer (2000) to destination: "); // 9

            System.out.println("Account source balance: "); // 10
            System.out.println("Account destination balance: "); // 11

            System.out.println("Bank balance: "); // 12

        } catch (AccountNotExists | OperationNotAllowed e) {
            e.printStackTrace();
        }
    }
}