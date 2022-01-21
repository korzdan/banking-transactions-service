package com.springproject.fileparserwebapp.services;

import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileUploadServiceTest {
    private File firstFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
    private File secondFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\Spring Project.docx");
    private File thirdFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");

    @Autowired
    private FileUploadService fileUploadService;

    @Test
    void uploadAllowedFiles() throws IOException {
        MultipartFile firstMultipartFile = new MockMultipartFile("first.xml", "xml_example.xml", "text/xml", new FileInputStream(firstFile));
        MultipartFile secondMultipartFile = new MockMultipartFile("second.docx", "Spring Project.docx",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document", new FileInputStream(secondFile));
        MultipartFile thirdMultipartFile = new MockMultipartFile("third.csv", "csv_example.csv", "text/csv", new FileInputStream(thirdFile));
        List<MultipartFile> allowedFiles = fileUploadService.uploadAllowedFiles(firstMultipartFile, secondMultipartFile, thirdMultipartFile);
        // Test extension of files
        for (MultipartFile file : allowedFiles) {
            assertNotEquals("docx", FilenameUtils.getExtension(file.getOriginalFilename()));
        }
    }
}