package com.springproject.fileparserwebapp.exception;

public class FileWritingException extends RuntimeException {
    public FileWritingException(String message) {
        super(message);
    }
}
