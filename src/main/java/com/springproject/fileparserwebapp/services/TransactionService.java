package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.exception.FileParserException;
import com.springproject.fileparserwebapp.exception.InvalidFileException;
import com.springproject.fileparserwebapp.exception.ParserNotFound;
import com.springproject.fileparserwebapp.exception.TransactionNotFound;
import com.springproject.fileparserwebapp.models.Statistics;
import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.parsers.Parser;
import com.springproject.fileparserwebapp.parsers.ParserFactory;
import com.springproject.fileparserwebapp.repos.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ParserFactory parserFactory;
    private final ErrorService errorService;
    private final FileService fileService;

    private final Set<String> successStatuses = Set.of("SUCCESS", "COMPLETE");
    private final Set<String> failureStatuses = Set.of("FAILURE", "FAILED", "REJECTED");

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> {
            throw new TransactionNotFound("Transaction hasn't been found.");
        });
    }

    public Transaction saveTransaction(Transaction transactionToSave) {
        return transactionRepository.save(transactionToSave);
    }

    public Iterable<Transaction> saveAllTransactions(List<Transaction> transactions) {
        return transactionRepository.saveAll(transactions);
    }

    public List<Transaction> getTopFiveTransaction() {
        return transactionRepository.findTop5ByOrderByAmountDesc();
    }

    public Transaction getMaxAmountTransaction() {
        return transactionRepository.findFirstByOrderByAmountDesc().orElseThrow(() -> {
            throw new TransactionNotFound("No transaction has been found.");
        });
    }

    public Statistics getTransactionsStatistics() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        return new Statistics((long)allTransactions.size(),
                getNumberOfSuccessfulTransactions(allTransactions),
                getNumberOfFailedTransactions(allTransactions),
                getMinAmountTransaction(),
                getMaxAmountTransaction());
    }

    public Transaction getMinAmountTransaction() {
        return transactionRepository.findFirstByOrderByAmountAsc().orElseThrow(() -> {
            throw new TransactionNotFound("No transaction has been found.");
        });
    }

    public List<Transaction> parseUploadedFiles(List<MultipartFile> files, User currentUser) {
        List<Transaction> transactions = new ArrayList<>();
        StringBuilder errorLog = new StringBuilder();
        for (MultipartFile file : files) {
            parseFileAndCatchExceptions(transactions, file, errorLog, currentUser);
        }
        transactionRepository.saveAll(transactions);
        throwExceptionIfErrorLogIsNotEmpty(errorLog);
        return transactions;
    }

    private void parseFileAndCatchExceptions(List<Transaction> transactions,
                                             MultipartFile file, StringBuilder errorLog, User currentUser) {
        try {
            Parser parser = parserFactory.getParser(file);
            transactions.addAll(parser.parse(file.getInputStream(), currentUser));
            fileService.saveUploadedFile(file);
        } catch (IOException e) {
            errorLog.append(" Cannot get InputStream from " + file.getOriginalFilename());
        } catch (FileParserException | InvalidFileException | ParserNotFound e) {
            errorLog.append(" " + file.getOriginalFilename() + e.getMessage());
            errorService.saveError(errorService.createError(file.getOriginalFilename() + e.getMessage()));
        }
    }

    private void throwExceptionIfErrorLogIsNotEmpty(StringBuilder errorLog) {
        if (errorLog.length() != 0) {
            errorLog.append(" Other files have been parsed.");
            throw new InvalidFileException(errorLog.toString());
        }
    }

    private long getNumberOfSuccessfulTransactions(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> successStatuses.contains(transaction.getStatus()))
                .count();
    }

    private long getNumberOfFailedTransactions(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> failureStatuses.contains(transaction.getStatus()))
                .count();
    }
}
