package com.springproject.fileparserwebapp.mappers;

import com.springproject.fileparserwebapp.dto.UserRequestDTO;
import com.springproject.fileparserwebapp.models.Status;
import com.springproject.fileparserwebapp.models.User;

public class UserRequestToUserMapper {
    public static User mapUserRequestDTOToUser(UserRequestDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setStatus(Status.ACTIVE);
        return user;
    }
}
