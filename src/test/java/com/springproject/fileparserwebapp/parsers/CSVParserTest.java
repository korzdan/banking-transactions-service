package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.models.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

class CSVParserTest {

    private CSVParser csvParser;

    @BeforeEach
    void setUp() {
        csvParser = new CSVParser();
    }

    @Test
    void parse() throws FileNotFoundException {
        File csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
        InputStream inputStream = new FileInputStream(csvFile);
        ArrayList<Transaction> listOfRecords = csvParser.parse(inputStream);
        Assertions.assertEquals(5, listOfRecords.size());
    }

    @Test
    void parseInvalidCsvFile() {
        File invalidCsvFile = new File("C:\\Users\\korzu\\Desktop\\csv_exception.csv");
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                csvParser.parse(new FileInputStream(invalidCsvFile)));
    }
}