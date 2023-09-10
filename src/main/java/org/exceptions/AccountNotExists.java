package org.exceptions;

public class AccountNotExists extends Exception{

    public AccountNotExists() {
        super("Konto nie istnieje.");
    }

    public AccountNotExists(String message) {
        super(message);
    }

    public AccountNotExists(String message, Throwable cause) {
        super(message, cause);
    }
}