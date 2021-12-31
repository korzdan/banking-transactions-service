package com.springproject.fileparserwebapp.parsers;

import org.junit.jupiter.api.Test;

import java.io.File;

class CSVParserTest {
    @Test
    void parseCSV() {
        ParserFactory factory = new CSVParserFactory();
        Parser CSVParser = factory.createParser();
        System.out.println(CSVParser.parse(new File("src/main/resources/files_to_parse/csv_example.csv")));
    }
}