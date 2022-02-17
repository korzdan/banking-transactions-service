package com.springproject.fileparserwebapp.controllers;

import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.services.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ErrorController {

    private final ErrorService errorService;

    @GetMapping("/errors")
    public ResponseEntity<?> getErrors() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(errorService.getAllErrorsCheckingCurrentUser(currentUser), HttpStatus.OK);
    }
}
