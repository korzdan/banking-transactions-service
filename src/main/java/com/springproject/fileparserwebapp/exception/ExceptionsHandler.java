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

    @ExceptionHandler(FileParserException.class)
    public ResponseEntity<Object> handleFileParserException(FileParserException e) {
        return new ResponseEntity<>(new Date() + " " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileWritingException.class)
    public ResponseEntity<Object> handleFileWritingException(FileWritingException e) {
        return new ResponseEntity<>(new Date() + " " + e.getMessage(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(ParserNotFound.class)
    public ResponseEntity<Object> handleParserNotFoundException(ParserNotFound e) {
        return new ResponseEntity<>(new Date() + " " + e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

}
