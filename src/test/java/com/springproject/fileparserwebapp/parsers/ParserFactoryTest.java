package com.springproject.fileparserwebapp.parsers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParserFactoryTest {

    @Autowired
    private ParserFactory factory;

    @Test
    void createParser() {
        File xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        File csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");

        Parser xmlParser = factory.createParser(xmlFile);
        Parser csvParser = factory.createParser(csvFile);

        assertEquals(XMLParser.class, xmlParser.getClass());
        assertEquals(CSVParser.class, csvParser.getClass());
    }
}