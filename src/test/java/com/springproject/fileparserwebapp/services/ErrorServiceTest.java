package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.models.Error;
import com.springproject.fileparserwebapp.models.Role;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.repos.ErrorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ErrorServiceTest {

    @Mock
    private ErrorRepository errorRepository;
    @InjectMocks
    private ErrorService errorService;

    @Test
    @DisplayName("Verify invocation of save() in Repository when calling saveError() in Service")
    void VerifyInvocationOfSaveInRepository_WhenCallingSaveErrorInService() {
        Error errorToSave = new Error();
        errorService.saveError(errorToSave);
        ArgumentCaptor<Error> errorArgumentCaptor =
                ArgumentCaptor.forClass(Error.class);
        verify(errorRepository).save(errorArgumentCaptor.capture());

        Error capturedError = errorArgumentCaptor.getValue();
        assertEquals(errorToSave, capturedError);
    }

    @Test
    @DisplayName("Return new error for calling createError method")
    void ReturnNewError_ForCreateErrorMethod() {
        String message = "Error message.";
        User user = new User();
        Error error = errorService.createError(message, user);
        assertEquals(new Error(user, message), error);
    }

    @Test
    @DisplayName("Return all errors when User is MANAGER")
    void ReturnAllErrors_WhenUserIsManager() {
        User manager = new User();
        manager.setRole(Role.MANAGER);

        errorService.getAllErrorsCheckingCurrentUser(manager);
        verify(errorRepository).findAll();
    }

    @Test
    @DisplayName("Return only errors of User when it's not MANAGER")
    void ReturnErrorsOfUser_WhenUserIsNotManager() {
        User operator = new User();
        operator.setRole(Role.OPERATOR);

        errorService.getAllErrorsCheckingCurrentUser(operator);
        verify(errorRepository).getErrorsByUserId(operator.getId());
    }

}