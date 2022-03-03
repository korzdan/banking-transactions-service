package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.exception.InvalidFileException;
import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

class CSVParserTest {

    private CSVParser csvParser;
    private File csvFile;
    private File csvExceptionFile;
    private File inappropriateFile;

    @BeforeEach
    void setUp() {
        csvParser = new CSVParser();
        csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
        csvExceptionFile = new File("C:\\Users\\korzu\\Desktop\\csv_exception.csv");
        inappropriateFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\file.txt");
    }

    @Test
    void ReturnFileExtensionFromParser() {
        Assertions.assertEquals("csv", csvParser.getAppropriateExtension());
    }

    @Test
    void Return5_When_CsvFileIsParsed() throws FileNotFoundException {
        ArrayList<Transaction> listOfRecords = csvParser.parse(new FileInputStream(csvFile), new User());
        Assertions.assertEquals(5, listOfRecords.size());
    }

    @Test
    void ThrowsInvalidFileException_ForIncorrectFile() throws InvalidFileException {
        InvalidFileException exception = Assertions.assertThrows(InvalidFileException.class, () ->
                csvParser.parse(new FileInputStream(csvExceptionFile), new User()));
        Assertions.assertEquals(" has some null values: it cannot be parsed.", exception.getMessage());
    }
}