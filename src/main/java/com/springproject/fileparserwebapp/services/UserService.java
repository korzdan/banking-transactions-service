package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.dto.UserRequestDTO;
import com.springproject.fileparserwebapp.exception.UsernameAlreadyExists;
import com.springproject.fileparserwebapp.models.Status;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist..."));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(UserRequestDTO userDto) {
        if (isExistByUsername(userDto.getUsername())) {
            throw new UsernameAlreadyExists("This username is already taken.");
        } else {
            return userRepository.save(mapUserRequestDtoToUser(userDto));
        }
    }

    private boolean isExistByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private User mapUserRequestDtoToUser(UserRequestDTO userDto) {
        User newUser = modelMapper.map(userDto, User.class);
        newUser.setStatus(Status.ACTIVE);
        return newUser;
    }

}
