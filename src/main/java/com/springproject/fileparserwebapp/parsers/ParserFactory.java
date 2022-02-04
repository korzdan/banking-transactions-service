package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.exception.InvalidFileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ParserFactory {
    private static final String XML_EXTENSION = "xml";
    private static final String CSV_EXTENSION = "csv";

    public Parser createParser(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (fileExtension.equals(XML_EXTENSION)) {
            return new XMLParser();
        }
        if (fileExtension.equals(CSV_EXTENSION)) {
            return new CSVParser();
        }
        throw new InvalidFileException(" has invalid file extension. Parser was not created.");
    }
}
