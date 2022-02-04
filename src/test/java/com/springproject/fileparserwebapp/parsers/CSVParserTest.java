package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.exception.InvalidFileException;
import com.springproject.fileparserwebapp.models.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

class CSVParserTest {

    private CSVParser csvParser;
    private File csvFile;
    private File csvExceptionFile;

    @BeforeEach
    void setUp() {
        csvParser = new CSVParser();
        csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
        csvExceptionFile = new File("C:\\Users\\korzu\\Desktop\\csv_exception.csv");
    }

    @Test
    void Return5_When_CsvFileIsParsed() throws FileNotFoundException {
        ArrayList<Transaction> listOfRecords = csvParser.parse(new FileInputStream(csvFile));
        Assertions.assertEquals(5, listOfRecords.size());
    }

    @Test
    void ThrowsInvalidFileException_ForIncorrectFile() {
        InvalidFileException exception = Assertions.assertThrows(InvalidFileException.class, () ->
                csvParser.parse(new FileInputStream(csvExceptionFile)));
        Assertions.assertEquals(" has some null values: it cannot be parsed.", exception.getMessage());
    }
}