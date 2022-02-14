package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.models.Error;
import com.springproject.fileparserwebapp.repos.ErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ErrorService {
    private final ErrorRepository errorRepository;

    public Error saveError(Error error) {
        return errorRepository.save(error);
    }

    public Error createError(Exception exception, String message) {
        String username;
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser instanceof UserDetails) {
            username = ((UserDetails)currentUser).getUsername();
        } else {
            username = currentUser.toString();
        }
        return new Error(username, exception.getClass().getSimpleName(), message);
    }

    public List<Error> getAllErrorsForManager() {
        return errorRepository.findAll();
    }

    public List<Error> getAllErrorsForOperator(String username) {
        List<Error> allErrors = errorRepository.findAll();
        List<Error> errorsListForOperator = new ArrayList<>();
        for (Error error : allErrors) {
            if (error.getUsername().equals(username)) {
                errorsListForOperator.add(error);
            }
        }
        return errorsListForOperator;
    }
}
