package com.springproject.fileparserwebapp.service;

import com.springproject.fileparserwebapp.model.Transaction;
import com.springproject.fileparserwebapp.parsers.XMLParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@SpringBootTest
class TransactionServiceTest {
    @Autowired
    private TransactionService service;

    @Test
    void findAllTransactions() {
        List<Transaction> list = service.findAllTransactions();
        System.out.println(list);
    }

    @Test
    void saveTransaction() {
        UUID transactionUUID = UUID.fromString("a4eeeb80-14fc-4142-b87d-e88386438a1b");
        UUID userUUID = UUID.fromString("b8e85137-4f3b-4a30-98f8-8be312ba74c6");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Transaction newTransaction = new Transaction(transactionUUID, userUUID, timestamp,
                10643, "USD", "FAILURE");
        Transaction returnedTransaction = service.saveTransaction(newTransaction);
        Assertions.assertEquals(newTransaction.getAmount(), returnedTransaction.getAmount());
    }

    @Test
    void parsing() {
        File file = new File("src/main/resources/files_to_parse/xml_example.xml");
        System.out.println(service.parseFileAndSaveTransactions(file));
    }
}