package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.controllers.AuthenticationRequestDTO;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public Map<Object, Object> getResponseWithToken(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (request.getUsername(), request.getPassword()));
        User user = (User) userService.loadUserByUsername(request.getUsername());
        String token = jwtTokenProvider.createToken(request.getUsername(), user.getRole().name());
        return createPositiveResponse(token, request.getUsername());
    }

    public Map<Object, Object> createNegativeResponse() {
        Map<Object, Object> response = new HashMap<>();
        response.put("date", new Date());
        response.put("message", "Invalid credentials.");
        return response;
    }

    private Map<Object, Object> createPositiveResponse(String token, String username) {
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        return response;
    }
}
