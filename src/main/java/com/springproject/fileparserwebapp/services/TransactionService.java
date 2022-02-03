package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.exception.ApiRequestExceptions;
import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.parsers.Parser;
import com.springproject.fileparserwebapp.parsers.ParserFactory;
import com.springproject.fileparserwebapp.repos.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
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

    public List<Transaction> parseUploadedFiles(List<MultipartFile> files) {
        List<Transaction> transactions = new ArrayList<>();
        // StringBuilder for collecting information about exceptions
        StringBuilder errorLog = new StringBuilder();

        // Parsing files and catching invalid files
        for (MultipartFile file : files) {
            try {
                Parser parser = parserFactory.createParser(file);
                transactions.addAll(parser.parse(file.getInputStream()));
            } catch (IOException e) {
                errorLog.append("Cannot get InputStream from " + file.getOriginalFilename());
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                errorLog.append(file.getOriginalFilename() + " is invalid. ");
            }
        }

        // Save parsed transactions to the database
        repository.saveAll(transactions);

        if (errorLog.length() != 0) throw new ApiRequestExceptions(errorLog.toString());
        return transactions;
    }
}
