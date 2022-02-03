package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.models.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    void findAllTransactions() {
        List<Transaction> list = transactionService.findAllTransactions();
        int sizeOfArray = list.size();
        Assertions.assertEquals(7, sizeOfArray);
    }

    @Test
    void saveTransaction() {
        UUID transactionUUID = UUID.fromString("a4eeeb80-14fc-4142-b87d-e88386438a1b");
        UUID userUUID = UUID.fromString("b8e85137-4f3b-4a30-98f8-8be312ba74c6");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Transaction newTransaction = new Transaction(transactionUUID, userUUID, timestamp,
                10643, "USD", "FAILURE");
        Transaction returnedTransaction = transactionService.saveTransaction(newTransaction);
        Assertions.assertEquals(newTransaction.getAmount(), returnedTransaction.getAmount());
    }

    @Test
    void parseUploadedFiles() throws IOException {
        // Finding appropriate files on the disk to parse
        File firstFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        File secondFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
        // Concerting the files to MultipartFile
        MultipartFile firstMultipartFile = new MockMultipartFile("first.xml",
                "xml_example.xml", "text/xml", new FileInputStream(firstFile));
        MultipartFile secondMultipartFile = new MockMultipartFile("third.csv",
                "csv_example.csv", "text/csv", new FileInputStream(secondFile));

        List<MultipartFile> files = new ArrayList<>();
        files.add(firstMultipartFile);
        files.add(secondMultipartFile);

        // Test of parsing uploaded files
        List<Transaction> listOfTransactions = transactionService.parseUploadedFiles(files);
        Assertions.assertEquals(7, listOfTransactions.size());

        // Test of saving parsed transactions to the Database
        List<Transaction> listOfParsedTransactions = (List<Transaction>) transactionService.saveAllTransactions(listOfTransactions);
        Assertions.assertEquals(7, listOfParsedTransactions.size());
    }
}