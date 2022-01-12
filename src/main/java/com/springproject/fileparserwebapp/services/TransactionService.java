package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.parsers.Parser;
import com.springproject.fileparserwebapp.parsers.ParserFactory;
import com.springproject.fileparserwebapp.repos.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    @Value("${upload.path}")
    private File uploadedFilesDir;
    private final TransactionRepository repository;
    private final ParserFactory parserFactory;

    public List<Transaction> findAllTransactions() {
        return repository.findAll();
    }

    public Transaction saveTransaction(Transaction transactionToSave) {
        return repository.save(transactionToSave);
    }

    public Iterable<Transaction> saveAllTransactions(List<Transaction> transactions) {
        return repository.saveAll(transactions);
    }

    public boolean parseUploadedFiles() {
        File[] listOfFiles = uploadedFilesDir.listFiles();
        for (File file : listOfFiles) {
            Parser parser = parserFactory.createParser(file);
            saveAllTransactions(parser.parse(file));
        }
        return true;
    }
}
