package com.springproject.fileparserwebapp.parsers;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserFactoryTest {

    private File firstFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
    private File secondFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");

    private ParserFactory factory = new ParserFactory();


    // TODO: WILL TEST EVERYTHING CORRECTLY AFTER CREATING APPROPRIATE PR

    @Test
    void createParser() throws IOException {
        MultipartFile firstMultipartFile = new MockMultipartFile("first.xml",
                "xml_example.xml", "text/xml", new FileInputStream(firstFile));
        MultipartFile secondMultipartFile = new MockMultipartFile("third.csv",
                "csv_example.csv", "text/csv", new FileInputStream(secondFile));

        Parser xmlParser = factory.getParser(firstMultipartFile);
        Parser csvParser = factory.getParser(secondMultipartFile);

        assertEquals(XMLParser.class, xmlParser.getClass());
        assertEquals(CSVParser.class, csvParser.getClass());
    }
}