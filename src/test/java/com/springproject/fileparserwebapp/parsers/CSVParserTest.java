package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.models.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;

@SpringBootTest
class CSVParserTest {

    private File csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
    @Autowired
    private CSVParser csvParser;

    @Test
    void parse() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(csvFile);
        ArrayList<Transaction> listOfRecords = csvParser.parse(inputStream);
        Assertions.assertEquals(5, listOfRecords.size());
    }
}