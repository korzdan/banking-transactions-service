package com.springproject.fileparserwebapp.controllers;

import com.springproject.fileparserwebapp.dto.UserRequestDTO;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.services.ErrorService;
import com.springproject.fileparserwebapp.services.FileService;
import com.springproject.fileparserwebapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@PreAuthorize("hasAuthority('execute_command')")
public class UserController {

    private final UserService userService;
    private final FileService fileService;
    private final ErrorService errorService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<?> getAllTransactionsOfUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getTransactionsOfUser(id), HttpStatus.OK);
    }

    @GetMapping("/{userId}/files")
    public ResponseEntity<?> getAllFilesUploadedByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(fileService.findAllFilesUploadedByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/errors")
    public ResponseEntity<?> getErrorsOfUser(@PathVariable Long userId) {
        return new ResponseEntity<>(errorService.getErrorsOfUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.registerUser(userDTO);
        return new ResponseEntity<>("User has been successfully registered!", HttpStatus.OK);
    }

}
