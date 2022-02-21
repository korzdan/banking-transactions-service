package com.springproject.fileparserwebapp.controllers;

import com.springproject.fileparserwebapp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    public final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            return new ResponseEntity<>(authenticationService.getResponseWithToken(request),
                    HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(authenticationService.createNegativeResponse(),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
