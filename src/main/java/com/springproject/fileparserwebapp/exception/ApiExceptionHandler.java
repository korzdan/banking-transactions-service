package com.springproject.fileparserwebapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestExceptions.class})
    public ResponseEntity<Object> handleFileParseException(ApiRequestExceptions e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.NOT_ACCEPTABLE,
                new Date()
        );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_ACCEPTABLE);
    }
}
