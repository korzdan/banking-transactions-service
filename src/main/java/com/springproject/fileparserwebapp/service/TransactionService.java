package com.springproject.fileparserwebapp.service;

import com.springproject.fileparserwebapp.model.Transaction;
import com.springproject.fileparserwebapp.parsers.*;
import com.springproject.fileparserwebapp.repos.TransactionRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Component
public class TransactionService {
    private static final String XML_EXTENSION = "xml";
    private static final String CSV_EXTENSION = "csv";

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<Transaction> findAllTransactions() {
        return repository.findAll();
    }

    public Transaction saveTransaction(Transaction transactionToSave) {
        return repository.save(transactionToSave);
    }

    public List<Transaction> parseFileAndSaveTransactions(File file) {
        String fileExtension = FilenameUtils.getExtension(file.getAbsolutePath());
        Parser parser = createParserSuitableForFile(fileExtension);
        return (List<Transaction>) repository.saveAll(parser.parse(file));
    }

    private static Parser createParserSuitableForFile(String fileExtension) {
        ParserFactory parserFactory;
        Parser parser = new XMLParser();
        if (fileExtension.equals(XML_EXTENSION)) {
            parserFactory = new XMLParserFactory();
            parser = parserFactory.createParser();
        } else if (fileExtension.equals(CSV_EXTENSION)) {
            parserFactory = new CSVParserFactory();
            parser = parserFactory.createParser();
        }
        return parser;
    }
}
