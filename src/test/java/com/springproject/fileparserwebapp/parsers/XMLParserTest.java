package com.springproject.fileparserwebapp.parsers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class XMLParserTest {

    @Test
    void parse() {
        XMLParser parser = new XMLParser();
        System.out.println(parser.parseTransactionsFromXML());
    }

}