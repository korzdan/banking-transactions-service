package com.springproject.fileparserwebapp.controllers;

import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('execute_command')")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<?> getAllTransactionsOfUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getTransactionsOfUser(id), HttpStatus.OK);
    }

}
