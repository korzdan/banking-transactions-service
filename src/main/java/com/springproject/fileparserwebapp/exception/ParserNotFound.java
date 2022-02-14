package com.springproject.fileparserwebapp.exception;

public class ParserNotFound extends RuntimeException {
    public ParserNotFound(String message) {
        super(message);
    }
}
