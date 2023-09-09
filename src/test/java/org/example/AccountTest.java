package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


class AccountTest {


    private Account account;

    @Before
    public void setUp() {
        account = new Account();
    }

    @Test
    public void testAccountCreation() { //Sprawdza, czy konto jest poprawnie tworzone, czy numer konta wynosi 1, a saldo wynosi 0.
        assertNotNull(account);
        assertEquals(1, account.getAccountNumber());
        assertEquals(0, account.getAccountBalance());
    }

    @Test
    public void testIncreaseBalance() {  //  Sprawdza, czy można poprawnie zwiększyć saldo na koncie i czy nowe saldo jest zgodne z oczekiwaniami.
        assertTrue(account.increaseBalance(100));
        assertEquals(100, account.getAccountBalance());
    }

    @Test
    public void testReduceBalance() { // Sprawdza, czy próba zwiększenia salda o ujemną kwotę jest obsługiwana poprawnie i czy saldo nie zmienia się.
        account.increaseBalance(200);
        assertTrue(account.reduceBalance(100));
        assertEquals(100, account.getAccountBalance());
    }

    @Test
    public void testInvalidIncreaseBalance() {  //  Sprawdza, czy próba zmniejszenia salda o kwotę większą niż dostępne saldo jest obsługiwana poprawnie i czy saldo nie zmienia się.
        assertFalse(account.increaseBalance(-50));
        assertEquals(0, account.getAccountBalance());
    }

    @Test
    public void testInvalidReduceBalance() {
        assertFalse(account.reduceBalance(100));
        assertEquals(0, account.getAccountBalance());
    }
}
