package com.springproject.fileparserwebapp.parsers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.springproject.fileparserwebapp.exception.FileParserException;
import com.springproject.fileparserwebapp.exception.InvalidFileException;
import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.utils.Utils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CSVParser implements Parser {
    @Override
    public ArrayList<Transaction> parse(InputStream inputStream, User currentUser) throws InvalidFileException, FileParserException {
        ArrayList<Transaction> listOfTransactions = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
            List<String[]> listOfRecords = csvReader.readAll();
            // Declaration of Transaction variables
            UUID transactionID;
            UUID userID;
            Timestamp timestamp;
            long amount;
            String currency;
            String status;
            // Going through List of records in order to generate Transaction
            // object and add it to returning List
            for (int i = 1; i < listOfRecords.size(); i++) {
                try {
                    timestamp = new Timestamp(Long.parseLong(listOfRecords.get(i)[0]));
                    transactionID = UUID.fromString(listOfRecords.get(i)[1]);
                    userID = UUID.fromString(listOfRecords.get(i)[2]);
                    amount = Long.parseLong(listOfRecords.get(i)[3]);
                    currency = listOfRecords.get(i)[4].toUpperCase();
                    status = listOfRecords.get(i)[5].toUpperCase();
                    listOfTransactions.add(new Transaction(transactionID, currentUser, userID, timestamp, amount, currency, status));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidFileException(" has some null values: it cannot be parsed.");
                }
            }
        } catch (IOException | CsvException e) {
            throw new FileParserException(" cannot be parsed: file is invalid.");
        }
        return listOfTransactions;
    }

    @Override
    public String getAppropriateExtension() {
        return "csv";
    }
}
