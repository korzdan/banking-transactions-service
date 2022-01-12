package com.springproject.fileparserwebapp.parsers;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ParserFactory {
    private static final String XML_EXTENSION = "xml";
    private static final String CSV_EXTENSION = "csv";

    public Parser createParser(File file) {
        String fileExtension = FilenameUtils.getExtension(file.getAbsolutePath());
        if (fileExtension.equals(XML_EXTENSION)) {
            return new XMLParser();
        }
        if (fileExtension.equals(CSV_EXTENSION)) {
            return new CSVParser();
        }
        throw new RuntimeException("Parser is not created");
    }
}
