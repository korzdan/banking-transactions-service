package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;

@SpringBootTest
class XMLParserTest {

    private File xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
    @Autowired
    private XMLParser xmlParser;

    @Test
    void parse() {
        ArrayList<Transaction> listOfRecords = xmlParser.parse(xmlFile);
        for (Transaction transaction : listOfRecords) {
            System.out.println(transaction);
        }
    }
}