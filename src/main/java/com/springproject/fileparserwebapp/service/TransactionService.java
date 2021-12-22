package com.springproject.fileparserwebapp.service;

import com.springproject.fileparserwebapp.model.Transaction;
import com.springproject.fileparserwebapp.parsers.CSVParser;
import com.springproject.fileparserwebapp.parsers.XMLParser;
import com.springproject.fileparserwebapp.repos.TransactionRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class TransactionService {
    private final TransactionRepository repository;
    private final XMLParser xmlParser;
    private final CSVParser csvParser;

    public TransactionService(TransactionRepository repository, XMLParser xmlParser, CSVParser csvParser) {
        this.repository = repository;
        this.xmlParser = xmlParser;
        this.csvParser = csvParser;
    }

    public List<Transaction> findAllTransactions() {
        return repository.findAll();
    }

    public Transaction saveTransaction(Transaction transactionToSave) {
        return repository.save(transactionToSave);
    }

    public List<Transaction> parseAndSaveTransactionsFromXML() {
        return (List<Transaction>) repository.saveAll(xmlParser.parseTransactionsFromXML());
    }

    public List<Transaction> parseAndSaveTransactionsFromCSV() {
        return (List<Transaction>) repository.saveAll(csvParser.parseTransactionsFromCSV());
    }

}
