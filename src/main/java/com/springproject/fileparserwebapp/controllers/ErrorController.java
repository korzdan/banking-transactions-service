package com.springproject.fileparserwebapp.controllers;

import com.springproject.fileparserwebapp.models.Error;
import com.springproject.fileparserwebapp.models.Role;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.services.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ErrorController {

    private final ErrorService errorService;

    @GetMapping("/errors")
    public ResponseEntity<?> getErrors() {
        List<Error> errorList = new ArrayList<>();
        User currentUser = (User)SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        if (currentUser.getRole().equals(Role.OPERATOR)) {
            errorList = errorService.getAllErrorsForOperator();
        }
        if (currentUser.getRole().equals(Role.MANAGER)) {
            errorList = errorService.getAllErrorsForManager();
        }

        return new ResponseEntity<>(errorList, HttpStatus.OK);
    }
}
