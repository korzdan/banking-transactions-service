package com.springproject.fileparserwebapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<?> handleInvalidFileException(InvalidFileException e) {
        return new ResponseEntity<>(new Date() + " " + e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(FileParserException.class)
    public ResponseEntity<?> handleFileParserException(FileParserException e) {
        return new ResponseEntity<>(new Date() + " " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileWritingException.class)
    public ResponseEntity<?> handleFileWritingException(FileWritingException e) {
        return new ResponseEntity<>(new Date() + " " + e.getMessage(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(ParserNotFound.class)
    public ResponseEntity<?> handleParserNotFoundException(ParserNotFound e) {
        return new ResponseEntity<>(new Date() + " " + e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(TransactionNotFound.class)
    public ResponseEntity<?> handleTransactionNotFound(TransactionNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFound exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
