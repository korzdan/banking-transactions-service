package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.exception.FileParserException;
import com.springproject.fileparserwebapp.exception.InvalidFileException;
import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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
    void ReturnFileExtensionFromParser() {
        Assertions.assertEquals("xml", xmlParser.getAppropriateExtension());
    }

    @Test
    void Return2Transactions_WhenXmlFileParse() throws IOException {
        List<Transaction> transactionList = xmlParser.parse(new FileInputStream(xmlFile), new User());
        Assertions.assertEquals(2, transactionList.size());
    }

    @Test
    void ThrowInvalidFileException_When_NoValuesInFile() {
        InvalidFileException exception = Assertions.assertThrows(InvalidFileException.class,
                () -> xmlParser.parse(new FileInputStream(exceptionXmlFile), new User()));
        Assertions.assertEquals(" has some null values: it cannot be parsed.", exception.getMessage());
    }

    @Test
    void ThrowInvalidFileException_When_NoTransactionIdInFile() {
        InvalidFileException exception = Assertions.assertThrows(InvalidFileException.class,
                () -> xmlParser.parse(new FileInputStream(xmlFileWithoutTransactionId), new User()));
        Assertions.assertEquals(" has some null values: it cannot be parsed.", exception.getMessage());
    }

    @Test
    void ThrowFileParserException_ForInvalidFile() {
        FileParserException exception = Assertions.assertThrows(FileParserException.class,
                () -> xmlParser.parse(new FileInputStream(invalidFile), new User()));
        Assertions.assertEquals(" cannot be parsed: file is invalid.", exception.getMessage());
    }
}