package com.springproject.fileparserwebapp.dto;

import com.springproject.fileparserwebapp.models.Role;
import com.springproject.fileparserwebapp.models.Status;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String username;
    private String password;
    private Role role;
    private Status status;
}
