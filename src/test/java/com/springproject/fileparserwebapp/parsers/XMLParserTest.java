package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.exception.FileParserException;
import com.springproject.fileparserwebapp.exception.InvalidFileException;
import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLParserTest {

    private XMLParser xmlParser;
    private File xmlFile;
    private File exceptionXmlFile;
    private File xmlFileWithoutTransactionId;
    private File invalidFile;

    @BeforeEach
    void setUp() {
        xmlParser = new XMLParser();
        xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        exceptionXmlFile = new File("C:\\Users\\korzu\\Desktop\\xml_exception.xml");
        xmlFileWithoutTransactionId = new File("C:\\Users\\korzu\\Desktop\\xml_withoutTransactionId.xml");
        invalidFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\file.txt");
    }

    @Test
    @DisplayName("Return xml for calling getAppropriateExtension()")
    void ReturnFileExtensionFromParser() {
        assertEquals("xml", xmlParser.getAppropriateExtension());
    }

    @Test
    @DisplayName("Return 2 parsed transactions from xml_example.xml when calling parse()")
    void Return2Transactions_WhenXmlFileParse() throws IOException {
        List<Transaction> transactionList = xmlParser.parse(new FileInputStream(xmlFile), new User());
        assertEquals(2, transactionList.size());
    }

    @Test
    @DisplayName("Throw InvalidFileException for calling parse() with file that has null values")
    void ThrowInvalidFileException_When_NoValuesInFile() {
        InvalidFileException exception = assertThrows(InvalidFileException.class,
                () -> xmlParser.parse(new FileInputStream(exceptionXmlFile), new User()));
        assertEquals(" has some null values: it cannot be parsed.", exception.getMessage());
    }

    @Test
    @DisplayName("Throw InvalidFileException for calling parse() with file that has no TransactionId")
    void ThrowInvalidFileException_When_NoTransactionIdInFile() {
        InvalidFileException exception = assertThrows(InvalidFileException.class,
                () -> xmlParser.parse(new FileInputStream(xmlFileWithoutTransactionId), new User()));
        assertEquals(" has some null values: it cannot be parsed.", exception.getMessage());
    }

    @Test
    @DisplayName("Throw FileParserException for invalid file when calling parse()")
    void ThrowFileParserException_ForInvalidFile() {
        FileParserException exception = assertThrows(FileParserException.class,
                () -> xmlParser.parse(new FileInputStream(invalidFile), new User()));
        assertEquals(" cannot be parsed: file is invalid.", exception.getMessage());
    }
}