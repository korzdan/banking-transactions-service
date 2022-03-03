package com.springproject.fileparserwebapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileUploadServiceTest {

    private FileUploadService fileUploadService = new FileUploadService();
    private File xmlFile;
    private File csvFile;
    private File incorrectFile;

    private MockMultipartFile xmlMultipart;
    private MockMultipartFile csvMultipart;
    private MockMultipartFile txtMultipart;

    @BeforeEach
    void setUp() throws Exception {
        xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
        incorrectFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\file.txt");

        xmlMultipart = new MockMultipartFile("file.xml",
                "xml_example.xml", "text/xml", new FileInputStream(xmlFile));
        csvMultipart = new MockMultipartFile("file.csv",
                "csv_example.csv", "text/csv", new FileInputStream(csvFile));
        txtMultipart = new MockMultipartFile("text.txt", "file.txt",
                "text/plain", new FileInputStream(incorrectFile));
    }

    @ParameterizedTest
    @ValueSource(strings = {"file.xml", "text.csv"})
    @DisplayName("Return true for files that can be parsed")
    void ReturnTrue_ForFilesThatCanBeParsed(String input) {
        assertEquals(true,
                ReflectionTestUtils.invokeMethod(fileUploadService, "isAllowedExtension", input));
    }

    @Test
    @DisplayName("Return List<MultipartFile> that are passed to uploadAllowedFiles(...)")
    void ReturnListOfPassedFiles_WhenCallingUploadAllowedFiles() {
        ReflectionTestUtils.setField(fileUploadService, "UPLOAD_FILE_DIR",
                "D:/Internship - ITechArtRep/Internship - Spring Boot project/src/main/resources/uploadedFiles");
        List<MultipartFile> files = List.of(xmlMultipart, csvMultipart, txtMultipart);
        assertEquals(files, fileUploadService.uploadAllowedFiles(xmlMultipart, csvMultipart, txtMultipart));
    }
}