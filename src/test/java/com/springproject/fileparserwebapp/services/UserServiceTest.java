package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.dto.UserRequestDTO;
import com.springproject.fileparserwebapp.exception.UserNotFound;
import com.springproject.fileparserwebapp.exception.UsernameAlreadyExists;
import com.springproject.fileparserwebapp.models.Role;
import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.repos.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private UserRequestDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserRequestDTO();
        userDTO.setUsername("vanya");
        userDTO.setPassword("1234");
        userDTO.setRole(Role.MANAGER);
    }

    @Test
    @DisplayName("Verify invocation of findAll() for calling findAllUsers() in Service")
    void VerifyCallOfFindAll_ForInvocationOfFindAllUsersInService() {
        userService.findAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("Return UserDetails for existing username when calling loadUserByUsername(...)")
    void ReturnUserDetails_ForExistingUsername() {
        String existingUsername = "vanya3";

        User user = new User(12L, "vanya3", "gwrnDFdf1G23");
        when(userRepository.findByUsername("vanya3")).thenReturn(Optional.of(user));

        UserDetails foundUser = userService.loadUserByUsername(existingUsername);
        assertEquals("gwrnDFdf1G23", foundUser.getPassword());
        assertEquals("vanya3", foundUser.getUsername());
    }

    @Test
    @DisplayName("Throw UsernameNotFound for nonexistent username when calling loadUserByUsername(...)")
    void ThrowUsernameNotFoundException_ForNonExistentUsername() throws UsernameNotFoundException {
        String nonExistentUsername = "petya";

        when(userRepository.findByUsername("petya")).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername(nonExistentUsername));

        assertEquals("User doesn't exist...", exception.getMessage());

    }

    @Test
    @DisplayName("Return User for calling findUserById(...) with existing ID")
    void ReturnUser_ForExistingId() {
        Long existingId = 1L;
        User userToFind = new User(existingId, "van2", "qwerty");

        when(userRepository.findById(existingId)).thenReturn(Optional.of(userToFind));

        User foundUser = userService.findUserById(existingId);
        assertEquals("qwerty", foundUser.getPassword());
        assertEquals("van2", foundUser.getUsername());
    }

    @Test
    @DisplayName("Throw UserNotFound for calling findUserById(...) with existing ID")
    void ThrowUserNotFoundException_ForNonExistentId() throws UserNotFound {
        Long nonExistentId = 0L;

        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        UserNotFound exception = assertThrows(UserNotFound.class,
                () -> userService.findUserById(nonExistentId));

        assertEquals("User hasn't been found.", exception.getMessage());
    }

    @Test
    @DisplayName("Return List<Transaction> for calling getTransactionsOfUser(...) with existing userId")
    void ReturnListOfTransactions_ForExistingUserId() {
        Long existingId = 2L;
        User userToFind = new User(existingId, "van", "qwe");

        Transaction transaction = new Transaction();
        transaction.setId(2L);

        userToFind.setTransactions(Set.of(transaction, new Transaction()));

        when(userRepository.findById(existingId)).thenReturn(Optional.of(userToFind));

        List<Transaction> listOfTransactions = userService.getTransactionsOfUser(existingId);
        assertEquals(2, listOfTransactions.size());
    }

    @Test
    @DisplayName("Return registered User when calling registerUser(...)")
    void ReturnUser_ForCallingRegisterUser() {
        User userFromDto = ReflectionTestUtils.invokeMethod(userService, "mapUserRequestDtoToUser", userDTO);

        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(Optional.empty());

        userService.registerUser(userDTO);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertEquals(userFromDto, capturedUser);
    }

    @Test
    @DisplayName("Throw UsernameAlreadyExists when calling registerUser(...) with existing User")
    void ThrowUsernameAlreadyExists_ForCallingRegisterUserWithExistingUser() {
        User userFromDto = ReflectionTestUtils.invokeMethod(userService, "mapUserRequestDtoToUser", userDTO);

        when(userRepository.findByUsername(userDTO.getUsername())).thenReturn(Optional.of(userFromDto));

        UsernameAlreadyExists exception = assertThrows(UsernameAlreadyExists.class,
                () -> userService.registerUser(userDTO));
        assertEquals("This username is already taken.", exception.getMessage());
    }
}