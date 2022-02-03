package com.springproject.fileparserwebapp.services;

import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUploadServiceTest {

    private FileUploadService fileUploadService;

    @BeforeEach
    void setUp() {
        fileUploadService = new FileUploadService();
    }

    @Test
    void uploadAllowedFiles() throws IOException {
        File firstFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        File secondFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\Spring Project.docx");
        File thirdFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");

        MultipartFile firstMultipartFile = new MockMultipartFile("first.xml", "xml_example.xml",
                "text/xml", new FileInputStream(firstFile));
        MultipartFile secondMultipartFile = new MockMultipartFile("second.docx", "Spring Project.docx",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document", new FileInputStream(secondFile));
        MultipartFile thirdMultipartFile = new MockMultipartFile("third.csv",
                "csv_example.csv", "text/csv", new FileInputStream(thirdFile));

        List<MultipartFile> allowedFiles = fileUploadService.uploadAllowedFiles(firstMultipartFile, secondMultipartFile, thirdMultipartFile);
        // Test extension of files
        for (MultipartFile file : allowedFiles) {
            assertNotEquals("docx", FilenameUtils.getExtension(file.getOriginalFilename()));
        }
    }
}