package org.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String customerId) {
            super("Klient o identyfikatorze '" + customerId + "' nie został znaleziony.");
        }

}
