package com.springproject.fileparserwebapp.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XMLParserTest {

    @Test
    void parse() {
        XMLParser parser = new XMLParser();
        parser.parseTransactions();
    }

}