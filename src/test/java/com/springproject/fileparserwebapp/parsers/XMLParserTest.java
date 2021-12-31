package com.springproject.fileparserwebapp.parsers;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

class XMLParserTest {

    @Test
    void parse() {
        ParserFactory factory = new XMLParserFactory();
        Parser parser = factory.createParser();
        System.out.println(parser.parse(new File("src/main/resources/files_to_parse/xml_example.xml")));
    }

}