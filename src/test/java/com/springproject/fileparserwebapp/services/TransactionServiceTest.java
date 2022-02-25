package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.repos.ErrorRepository;
import com.springproject.fileparserwebapp.repos.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private ErrorRepository errorRepository;
    private ErrorService errorService;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transactionToSave;
    private File xmlFile;
    private File csvFile;

    @BeforeEach
    void setUp() {
        transactionToSave = new Transaction(
                UUID.fromString("a4eeeb80-14fc-4142-b87d-e88386438a1b"),
                UUID.fromString("b8e85137-4f3b-4a30-98f8-8be312ba74c6"),
                new Timestamp(System.currentTimeMillis()),
                10643,
                "USD",
                "FAILURE");
        xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
    }

    @Test
    void VerifyInvocationFindAllInRepo_WhenInvokeMethodInService() {
        transactionService.findAllTransactions();
        verify(transactionRepository).findAll();
    }

    @Test
    void CapturedTransaction_EqualsTo_SavedTransaction() {
        transactionService.saveTransaction(transactionToSave);
        ArgumentCaptor<Transaction> transactionArgumentCaptor =
                ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository).save(transactionArgumentCaptor.capture());

        Transaction capturedTransaction = transactionArgumentCaptor.getValue();
        Assertions.assertEquals(transactionToSave, capturedTransaction);
    }

    @Test
    void CapturedList_EqualsTo_SavedListOfTransactions() {
        transactionService.saveAllTransactions(List.of(transactionToSave));
        ArgumentCaptor<List<Transaction>> listArgumentCaptor =
                ArgumentCaptor.forClass(List.class);
        verify(transactionRepository).saveAll(listArgumentCaptor.capture());

        List<Transaction> capturedList = listArgumentCaptor.getValue();
        Assertions.assertEquals(List.of(transactionToSave), capturedList);
    }

    @Test
    //TODO: Fix the test
    void VerifyInvocationOfSaveAll_WhenParsingUploadedFiles() throws IOException {
        MultipartFile firstMultipartFile = new MockMultipartFile("first.xml",
                "xml_example.xml", "text/xml", new FileInputStream(xmlFile));
        MultipartFile secondMultipartFile = new MockMultipartFile("second.csv",
                "csv_example.csv", "text/csv", new FileInputStream(csvFile));

        List<MultipartFile> files = new ArrayList<>();
        files.add(firstMultipartFile);
        files.add(secondMultipartFile);

        List<Transaction> transactionList = transactionService.parseUploadedFiles(files);
        verify(transactionRepository, times(1)).saveAll(transactionList);
    }
}