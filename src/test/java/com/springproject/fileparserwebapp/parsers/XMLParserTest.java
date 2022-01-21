package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.models.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

@SpringBootTest
class XMLParserTest {

    private File xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
    @Autowired
    private XMLParser xmlParser;

    @Test
    void parse() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(xmlFile);
        ArrayList<Transaction> listOfRecords = xmlParser.parse(inputStream);
        Assertions.assertEquals(2, listOfRecords.size());
    }
}