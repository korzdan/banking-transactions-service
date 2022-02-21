package com.springproject.fileparserwebapp.exception;

public class TransactionNotFound extends RuntimeException {
    public TransactionNotFound(String message) {
        super(message);
    }
}
