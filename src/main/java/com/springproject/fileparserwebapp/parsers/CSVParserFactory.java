package com.springproject.fileparserwebapp.parsers;

public class CSVParserFactory implements ParserFactory {
    @Override
    public Parser createParser() {
        return new CSVParser();
    }
}
