package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.models.Error;
import com.springproject.fileparserwebapp.models.Role;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.repos.ErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public Error createError(String message) {
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Error((User)currentUser, message);
    }

    public List<Error> getAllErrorsForManager() {
        return errorRepository.findAll();
    }

    public List<Error> getAllErrorsForOperator() {
        List<Error> allErrors = errorRepository.findAll();
        List<Error> errorsListForOperator = new ArrayList<>();
        for (Error error : allErrors) {
            if (error.getUser().getRole().equals(Role.OPERATOR)) {
                errorsListForOperator.add(error);
            }
        }
        return errorsListForOperator;
    }
}
