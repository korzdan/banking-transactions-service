package com.springproject.fileparserwebapp.parsers;

import com.springproject.fileparserwebapp.model.Transaction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVParserTest {
    @Test
    void parseCSV() {
        CSVParser parser = new CSVParser();
        List<Transaction> list = parser.parseTransactionsFromCSV();
        for (Transaction transaction : list) {
            System.out.println(transaction);
        }
    }
}