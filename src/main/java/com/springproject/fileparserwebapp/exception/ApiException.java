package com.springproject.fileparserwebapp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@RequiredArgsConstructor
@Getter
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final Date timestamp;
}
