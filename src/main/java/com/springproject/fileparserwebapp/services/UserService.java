package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.dto.UserRequestDTO;
import com.springproject.fileparserwebapp.exception.UsernameAlreadyExists;
import com.springproject.fileparserwebapp.mappers.UserRequestToUserMapper;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist..."));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(UserRequestDTO userDto) {
        try {
            loadUserByUsername(userDto.getUsername());
            throw new UsernameAlreadyExists("A user with such a username already exists.");
        } catch (UsernameNotFoundException e) {
            return userRepository.save(UserRequestToUserMapper.mapUserRequestDTOToUser(userDto));
        }
    }

}
