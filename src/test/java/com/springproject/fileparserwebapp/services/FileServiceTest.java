package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.models.File;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.repos.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @Mock
    private FileRepository fileRepository;
    @InjectMocks
    private FileService fileService;

    private java.io.File file;

    @BeforeEach
    void setUp() {
        file = new java.io.File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\file.txt");
    }

    @Test
    @DisplayName("Return all files uploaded by User for calling findAllFilesUploadedByUser(...)")
    void ReturnAllFilesUploadedByUser_ForCallingTheMethod() {
        Long userId = 22L;
        fileService.findAllFilesUploadedByUser(userId);
        verify(fileRepository).getAllByUserId(userId);
    }

    @Test
    @DisplayName("Return saved to db File for invocation of saveUploadedFile(...)")
    void ReturnSavedFile_WhenCallingSaveUploadedFile() throws IOException {
        String name = "file.txt";
        User user = new User();
        File savedFile = new File(name, user);

        MockMultipartFile fileMultipart =
                new MockMultipartFile(name, name, "text/plain", new FileInputStream(file));

        fileService.saveUploadedFile(fileMultipart, user);
        verify(fileRepository).save(savedFile);
    }
}