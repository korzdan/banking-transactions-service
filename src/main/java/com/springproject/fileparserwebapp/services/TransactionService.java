package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.exception.FileParserException;
import com.springproject.fileparserwebapp.exception.InvalidFileException;
import com.springproject.fileparserwebapp.exception.ParserNotFound;
import com.springproject.fileparserwebapp.exception.TransactionNotFound;
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

    public List<Transaction> getTopFiveTransaction() {
        return transactionRepository.getTopFiveTransactions();
    }

    public Transaction getMaxAmountTransaction() {
        return transactionRepository.getMaxAmountTransaction().orElseThrow(() -> {
            throw new TransactionNotFound("No transaction has been found.");
        });
    }

    public String getTransactionStatistic() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        int numberOfTransactions = allTransactions.size();
        int numberOfSuccessfulTransactions = getNumberOfSuccessfulTransactions(allTransactions);
        int numberOfFailedTransactions = getNumberOfFailedTransactions(allTransactions);
        return createStatistics(numberOfTransactions, numberOfSuccessfulTransactions, numberOfFailedTransactions);
    }

    public Transaction getMinAmountTransaction() {
        return transactionRepository.getMinAmountTransaction().orElseThrow(() -> {
            throw new TransactionNotFound("No transaction has been found.");
        });
    }

    public List<Transaction> parseUploadedFiles(List<MultipartFile> files) {
        List<Transaction> transactions = new ArrayList<>();
        StringBuilder errorLog = new StringBuilder();
        for (MultipartFile file : files) {
            parseFileAndCatchExceptions(transactions, file, errorLog);
        }
        transactionRepository.saveAll(transactions);
        throwExceptionIfErrorLogIsNotEmpty(errorLog);
        return transactions;
    }

    private void parseFileAndCatchExceptions(List<Transaction> transactions,
                                             MultipartFile file, StringBuilder errorLog) {
        try {
            Parser parser = parserFactory.getParser(file);
            transactions.addAll(parser.parse(file.getInputStream()));
        } catch (IOException e) {
            errorLog.append(" Cannot get InputStream from " + file.getOriginalFilename());
        } catch (FileParserException | InvalidFileException | ParserNotFound e) {
            errorLog.append(" " + file.getOriginalFilename() + e.getMessage());
        }
    }

    private void throwExceptionIfErrorLogIsNotEmpty(StringBuilder errorLog) {
        if (errorLog.length() != 0) {
            errorLog.append(" Other files have been parsed.");
            throw new InvalidFileException(errorLog.toString());
        }
    }

    private int getNumberOfSuccessfulTransactions(List<Transaction> transactions) {
        int numberOfSuccessfulTransactions = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getStatus().equals("SUCCESS") || transaction.getStatus().equals("COMPLETE")) {
                numberOfSuccessfulTransactions++;
            }
        }
        return numberOfSuccessfulTransactions;
    }

    private int getNumberOfFailedTransactions(List<Transaction> transactions) {
        int numberOfSuccessfulTransactions = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getStatus().equals("FAILED") || transaction.getStatus().equals("REJECTED") ||
                    transaction.getStatus().equals("FAILURE")) {
                numberOfSuccessfulTransactions++;
            }
        }
        return numberOfSuccessfulTransactions;
    }

    private String createStatistics(int numberOfTransactions, int numberOfSuccessfulTransactions,
                                    int numberOfFailedTransactions) {
        return "Total number of transactions: " + numberOfTransactions +
                ". Where successful: " + numberOfSuccessfulTransactions +
                " failed: " + numberOfFailedTransactions +
                " Min transaction: " + getMinAmountTransaction().getAmount() +
                " Max transaction: " + getMaxAmountTransaction().getAmount();
    }
}
