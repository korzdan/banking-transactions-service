package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.repos.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void VerifyCallOfFindAll_ForInvocationOfFindAllUsersInService() {
        userService.findAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    void ReturnUserDetails_ForExistingUsername() {
        String existingUsername = "vanya3";

        User user = new User(12L, "vanya3", "gwrnDFdf1G23");
        when(userRepository.findByUsername("vanya3")).thenReturn(Optional.of(user));

        UserDetails foundUser = userService.loadUserByUsername(existingUsername);
        Assertions.assertEquals("gwrnDFdf1G23", foundUser.getPassword());
        Assertions.assertEquals("vanya3", foundUser.getUsername());
    }

    @Test
    void ThrowUsernameNotFoundException_ForNonExistentUsername() {
        String nonExistentUsername = "petya";

        when(userRepository.findByUsername("petya")).thenThrow(UsernameNotFoundException.class);

        Assertions.assertThrows(UsernameNotFoundException.class, () ->
                userService.loadUserByUsername(nonExistentUsername));
    }
}