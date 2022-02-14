package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.exception.FileParserException;
import com.springproject.fileparserwebapp.exception.InvalidFileException;
import com.springproject.fileparserwebapp.exception.ParserNotFound;
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
    private final TransactionRepository transactionRepository;
    private final ErrorService errorService;
    private final ParserFactory parserFactory;

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction saveTransaction(Transaction transactionToSave) {
        return transactionRepository.save(transactionToSave);
    }

    public Iterable<Transaction> saveAllTransactions(List<Transaction> transactions) {
        return transactionRepository.saveAll(transactions);
    }

    public List<Transaction> parseUploadedFiles(List<MultipartFile> files) {
        List<Transaction> transactions = new ArrayList<>();
        // StringBuilder for collecting information about exceptions
        StringBuilder errorLog = new StringBuilder();

        // Parsing files and catching invalid files
        for (MultipartFile file : files) {
            try {
                Parser parser = parserFactory.getParser(file);
                transactions.addAll(parser.parse(file.getInputStream()));
            } catch (IOException e) {
                errorLog.append(" Cannot get InputStream from " + file.getOriginalFilename());
            } catch (FileParserException | InvalidFileException | ParserNotFound e) {
                String messageToDatabase = file.getOriginalFilename() + e.getMessage();
                errorService.saveError(errorService.createError(e, messageToDatabase));
                errorLog.append(" " + file.getOriginalFilename() + e.getMessage());
            }
        }

        // Save parsed transactions to the database
        transactionRepository.saveAll(transactions);

        if (errorLog.length() != 0) {
            errorLog.append(" Other files have been parsed.");
            throw new InvalidFileException(errorLog.toString());
        }
        return transactions;
    }
}
