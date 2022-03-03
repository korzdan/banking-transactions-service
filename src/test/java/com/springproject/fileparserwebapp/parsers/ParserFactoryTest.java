package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.exception.ParserNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserFactoryTest {

    private ParserFactory factory;
    private File xmlFile;
    private File csvFile;
    private File incorrectFile;

    @BeforeEach
    void setUp() {
        factory = new ParserFactory();
        xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
        incorrectFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\file.txt");
    }

    @Test
    @DisplayName("Return XMLParser for xml extension file")
    void ReturnXMLParser_ForXmlFile() throws IOException {
        MultipartFile xmlMultipart = new MockMultipartFile("file.xml",
                "xml_example.xml", "text/xml", new FileInputStream(xmlFile));
        Parser xmlParser = factory.getParser(xmlMultipart);
        assertEquals(XMLParser.class, xmlParser.getClass());
    }

    @Test
    @DisplayName("Return CSVParser for csv extension file")
    void ReturnCSVParser_ForCSVFile() throws IOException {
        MultipartFile csvMultipart = new MockMultipartFile("file.csv",
                "csv_example.csv", "text/csv", new FileInputStream(csvFile));
        Parser xmlParser = factory.getParser(csvMultipart);
        assertEquals(CSVParser.class, xmlParser.getClass());
    }

    @Test
    @DisplayName("Throw ParserNotFound for unsupported file extension")
    void ThrowParserNotFound_ForIncorrectExtensionOfFile() throws IOException {
        MultipartFile incorrectMultipart = new MockMultipartFile("text.txt", "file.txt",
                "text/plain", new FileInputStream(incorrectFile));
        ParserNotFound exception = assertThrows(ParserNotFound.class,
                () -> factory.getParser(incorrectMultipart));
        assertEquals(" suitable parser hasn't been found for this file.", exception.getMessage());
    }
}