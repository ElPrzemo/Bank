package org.example.exceptions;

public class OperationNotAllowed extends Exception {


    public OperationNotAllowed(String message) {
        super(message);
    }

    public OperationNotAllowed(String message, Throwable cause) {
        super(message, cause);
    }
}

