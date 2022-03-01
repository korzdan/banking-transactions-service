package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.exception.UserNotFound;
import com.springproject.fileparserwebapp.models.Transaction;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    void ThrowUsernameNotFoundException_ForNonExistentUsername() throws UsernameNotFoundException {
        String nonExistentUsername = "petya";

        when(userRepository.findByUsername("petya")).thenReturn(Optional.empty());

        UsernameNotFoundException exception = Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername(nonExistentUsername));

        Assertions.assertEquals("User doesn't exist...", exception.getMessage());

    }

    @Test
    void ReturnUser_ForExistingId() {
        Long existingId = 1L;
        User userToFind = new User(existingId, "van2", "qwerty");

        when(userRepository.findById(existingId)).thenReturn(Optional.of(userToFind));

        User foundUser = userService.findUserById(existingId);
        Assertions.assertEquals("qwerty", foundUser.getPassword());
        Assertions.assertEquals("van2", foundUser.getUsername());
    }

    @Test
    void ThrowUsernameNotFoundException_ForNonExistentId() throws UserNotFound {
        Long nonExistentId = 0L;

        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        UserNotFound exception = Assertions.assertThrows(UserNotFound.class,
                () -> userService.findUserById(nonExistentId));

        Assertions.assertEquals("User hasn't been found.", exception.getMessage());
    }

    @Test
    void ReturnListOfTransactions_ForExistingUserId() {
        Long existingId = 2L;
        User userToFind = new User(existingId, "van", "qwe");
        userToFind.setTransactions(Set.of(new Transaction(), new Transaction()));

        when(userRepository.findById(existingId)).thenReturn(Optional.of(userToFind));

        List<Transaction> listOfTransactions = userService.getTransactionsOfUser(existingId);
        Assertions.assertEquals(2, listOfTransactions.size());
    }
}