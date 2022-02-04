package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.exception.InvalidFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserFactoryTest {

    private ParserFactory factory;
    private File xmlFile;
    private File csvFile;
    private File fileWithUnsupportedExtension;

    @BeforeEach
    void setUp() {
        factory = new ParserFactory();
        xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
        fileWithUnsupportedExtension = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\file.txt");
    }

    @Test
    void CreateXmlParser_ForXmlFile() throws IOException {
        MultipartFile xmlMockFile = new MockMultipartFile("first.xml",
                "xml_example.xml", "text/xml", new FileInputStream(xmlFile));
        Parser parser = factory.createParser(xmlMockFile);
        assertEquals(XMLParser.class, parser.getClass());
    }

    @Test
    void CreateCsvParser_ForCsvFile() throws IOException {
        MultipartFile csvMockFile = new MockMultipartFile("second.csv",
                "csv_example.csv", "text/csv", new FileInputStream(csvFile));
        Parser parser = factory.createParser(csvMockFile);
        assertEquals(CSVParser.class, parser.getClass());
    }

    @Test
    void ThrowInvalidFileException_ForUnsupportedFileExtension() throws IOException {
        MultipartFile unsupportedExtensionMock = new MockMultipartFile("third.txt",
                "file.txt", "text/plain", new FileInputStream(fileWithUnsupportedExtension));
        InvalidFileException exception = assertThrows(InvalidFileException.class, () ->
                factory.createParser(unsupportedExtensionMock));
        assertEquals(" has invalid file extension. Parser was not created.", exception.getMessage());
    }
}