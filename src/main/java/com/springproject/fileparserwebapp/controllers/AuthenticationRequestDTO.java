package com.springproject.fileparserwebapp.controllers;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String username;
    private String password;
}
