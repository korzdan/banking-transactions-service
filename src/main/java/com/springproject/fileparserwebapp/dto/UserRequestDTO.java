package com.springproject.fileparserwebapp.dto;

import com.springproject.fileparserwebapp.models.Role;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String username;
    private String password;
    private Role role;
}
