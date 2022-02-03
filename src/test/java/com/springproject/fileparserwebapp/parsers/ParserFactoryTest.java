package com.springproject.fileparserwebapp.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserFactoryTest {

    private ParserFactory factory;

    @BeforeEach
    void setUp() {
        factory = new ParserFactory();
    }

    @Test
    void createParser() throws IOException {
        File firstFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        File secondFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");

        MultipartFile firstMultipartFile = new MockMultipartFile("first.xml",
                "xml_example.xml", "text/xml", new FileInputStream(firstFile));
        MultipartFile secondMultipartFile = new MockMultipartFile("third.csv",
                "csv_example.csv", "text/csv", new FileInputStream(secondFile));

        Parser xmlParser = factory.createParser(firstMultipartFile);
        Parser csvParser = factory.createParser(secondMultipartFile);

        assertEquals(XMLParser.class, xmlParser.getClass());
        assertEquals(CSVParser.class, csvParser.getClass());
    }
}