package com.springproject.fileparserwebapp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class FileUploadServiceTest {

    @Autowired
    private FileUploadService fileUploadService;
    private File xmlFile;
    private File csvFile;
    private File docxFile;

    @BeforeEach
    void setUp() {
        xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\Spring Project.docx");
        docxFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
    }

    @Test
    void Return3_WhenAllowedFilesUploadedAndAllFilesReturned() throws IOException {
        MultipartFile firstMultipartFile = new MockMultipartFile("first.xml", "xml_example.xml",
                "text/xml", new FileInputStream(xmlFile));
        MultipartFile secondMultipartFile = new MockMultipartFile("second.docx", "Spring Project.docx",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document", new FileInputStream(csvFile));
        MultipartFile thirdMultipartFile = new MockMultipartFile("third.csv",
                "csv_example.csv", "text/csv", new FileInputStream(docxFile));

        List<MultipartFile> allowedFiles = fileUploadService.uploadAllowedFiles(firstMultipartFile,
                secondMultipartFile, thirdMultipartFile);
        Assertions.assertEquals(3, allowedFiles.size());
    }
}