package com.springproject.fileparserwebapp.parsers;

public class XMLParserFactory implements ParserFactory {
    @Override
    public Parser createParser() {
        return new XMLParser();
    }
}
