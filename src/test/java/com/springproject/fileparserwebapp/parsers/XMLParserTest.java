package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.models.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

class XMLParserTest {
    private XMLParser xmlParser;

    @BeforeEach
    void setUp() {
        xmlParser = new XMLParser();
    }

    @Test
    void parse() throws FileNotFoundException {
        File xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");

        InputStream inputStream = new FileInputStream(xmlFile);
        ArrayList<Transaction> listOfRecords = xmlParser.parse(inputStream);
        Assertions.assertEquals(2, listOfRecords.size());
    }

    @Test
    void parseInvalidXmlFile() {
        File exceptionXmlFile = new File("C:\\Users\\korzu\\Desktop\\xml_exception.xml");
        Assertions.assertThrows(NullPointerException.class, () -> xmlParser.parse(new FileInputStream(exceptionXmlFile)));
    }

    @Test
    void parseFileWithoutTransactionId() {
        File exceptionXmlFile = new File("C:\\Users\\korzu\\Desktop\\xml_withoutTransactionId.xml");
        Assertions.assertThrows(NullPointerException.class, () -> xmlParser.parse(new FileInputStream(exceptionXmlFile)));
    }
}