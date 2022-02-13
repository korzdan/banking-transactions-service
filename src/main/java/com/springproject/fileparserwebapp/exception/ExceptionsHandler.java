package com.springproject.fileparserwebapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Object> handleInvalidFileException(InvalidFileException e) {
        return new ResponseEntity<>(new Date() + " " + e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {FileParserException.class, FileWritingException.class})
    public ResponseEntity<Object> handleFileParserAndFileWritingExceptions(RuntimeException e) {
        return new ResponseEntity<>(new Date() + " " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
