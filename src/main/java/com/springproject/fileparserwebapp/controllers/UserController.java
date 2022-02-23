package com.springproject.fileparserwebapp.controllers;

import com.springproject.fileparserwebapp.models.File;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.services.FileService;
import com.springproject.fileparserwebapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FileService fileService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('execute_command')")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("users/{userId}/files")
    public ResponseEntity<List<File>> getAllFilesUploadedByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(fileService.findAllFilesUploadedByUser(userId), HttpStatus.OK);
    }

}
