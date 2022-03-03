package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.exception.TransactionNotFound;
import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.parsers.ParserFactory;
import com.springproject.fileparserwebapp.repos.ErrorRepository;
import com.springproject.fileparserwebapp.repos.FileRepository;
import com.springproject.fileparserwebapp.repos.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private ErrorRepository errorRepository;
    @Mock
    private FileRepository fileRepository;

    private TransactionService transactionService;

    private Transaction transactionToSave;
    private File xmlFile;
    private File csvFile;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService(transactionRepository,
                new ParserFactory(),
                new ErrorService(errorRepository),
                new FileService(fileRepository));

        transactionToSave = new Transaction(
                UUID.fromString("a4eeeb80-14fc-4142-b87d-e88386438a1b"),
                new User(),
                UUID.fromString("b8e85137-4f3b-4a30-98f8-8be312ba74c6"),
                new Timestamp(System.currentTimeMillis()),
                10643,
                "USD",
                "FAILURE");

        xmlFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\xml_example.xml");
        csvFile = new File("D:\\Internship - ITechArtRep\\Spring Project Info\\FilesExample\\csv_example.csv");
    }

    @Test
    @DisplayName("Verify invocation of findAll() when calling findAllTransactions() in Service")
    void VerifyInvocationFindAllInRepo_WhenInvokeMethodInService() {
        transactionService.findAllTransactions();
        verify(transactionRepository).findAll();
    }

    @Test
    @DisplayName("Verify invocation of findTop5ByOrderByAmountDesc() when calling getTopFiveTransactions() in Service")
    void ReturnTop5Transactions_ForCallingGetTopFiveTransactions() {
        transactionService.getTopFiveTransactions();
        verify(transactionRepository).findTop5ByOrderByAmountDesc();
    }

    @Test
    @DisplayName("Return Transaction for calling findTransactionById(...) with existing ID")
    void ReturnTransaction_ForExistingId() {
        Long id = 1L;
        Transaction transaction = new Transaction();

        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));

        assertEquals(transaction, transactionService.findTransactionById(id));
    }

    @Test
    @DisplayName("Throw TransactionNotFound for calling findTransactionById(...) with nonexistent ID")
    void ThrowTransactionNotFound_ForNonexistentId() {
        Long id = 2L;

        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        TransactionNotFound exception = assertThrows(TransactionNotFound.class,
                () -> transactionService.findTransactionById(id));
        assertEquals("Transaction hasn't been found.", exception.getMessage());
    }

    @Test
    @DisplayName("Verify invocation of findFirstByOrderByAmountDesc() when calling getMaxAmountTransaction()")
    void ReturnTopTransaction_WhenCallingGetMaxAmountTransaction() {
        Transaction maxTransaction = new Transaction();

        when(transactionRepository.findFirstByOrderByAmountDesc()).thenReturn(Optional.of(maxTransaction));

        assertEquals(maxTransaction, transactionService.getMaxAmountTransaction());
    }

    @Test
    @DisplayName("Throw TransactionNotFound when calling getMaxAmountTransaction()")
    void ThrowTransactionNotFound_ForGetMaxAmountTransaction() {
        when(transactionRepository.findFirstByOrderByAmountDesc()).thenReturn(Optional.empty());

        TransactionNotFound exception = assertThrows(TransactionNotFound.class,
                () -> transactionService.getMaxAmountTransaction());
        assertEquals("No transaction has been found.", exception.getMessage());
    }

    @Test
    @DisplayName("Verify invocation of findFirstByOrderByAmountAsc() when calling getMinAmountTransaction()")
    void ReturnMinTransaction_WhenCallingGetMixAmountTransaction() {
        Transaction minTransaction = new Transaction();

        when(transactionRepository.findFirstByOrderByAmountAsc()).thenReturn(Optional.of(minTransaction));

        assertEquals(minTransaction, transactionService.getMinAmountTransaction());
    }

    @Test
    @DisplayName("Throw TransactionNotFound when calling getMinAmountTransaction()")
    void ThrowTransactionNotFound_ForCallingGetMinAmountTransaction() {
        when(transactionRepository.findFirstByOrderByAmountAsc()).thenReturn(Optional.empty());

        TransactionNotFound exception = assertThrows(TransactionNotFound.class,
                () -> transactionService.getMinAmountTransaction());
        assertEquals("No transaction has been found.", exception.getMessage());
    }

    @Test
    @DisplayName("Captured transaction equals to saving transaction when calling saveTransaction(...)")
    void CapturedTransaction_EqualsTo_SavedTransaction() {
        transactionService.saveTransaction(transactionToSave);
        ArgumentCaptor<Transaction> transactionArgumentCaptor =
                ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository).save(transactionArgumentCaptor.capture());

        Transaction capturedTransaction = transactionArgumentCaptor.getValue();
        assertEquals(transactionToSave, capturedTransaction);
    }

    @Test
    @DisplayName("Captured List<Transaction> equals to saving transactions when calling saveAllTransactions(...)")
    void CapturedList_EqualsTo_SavedListOfTransactions() {
        transactionService.saveAllTransactions(List.of(transactionToSave));
        ArgumentCaptor<List<Transaction>> listArgumentCaptor =
                ArgumentCaptor.forClass(List.class);
        verify(transactionRepository).saveAll(listArgumentCaptor.capture());

        List<Transaction> capturedList = listArgumentCaptor.getValue();
        assertEquals(List.of(transactionToSave), capturedList);
    }

    @Test
    @DisplayName("Verify invocation of saveAll(...) when calling parseUploadedFiles(...)")
    void VerifyInvocationOfSaveAll_WhenParsingUploadedFiles() throws IOException {
        MultipartFile firstMultipartFile = new MockMultipartFile("first.xml",
                "xml_example.xml", "text/xml", new FileInputStream(xmlFile));
        MultipartFile secondMultipartFile = new MockMultipartFile("second.csv",
                "csv_example.csv", "text/csv", new FileInputStream(csvFile));

        List<MultipartFile> files = new ArrayList<>();
        files.add(firstMultipartFile);
        files.add(secondMultipartFile);

        List<Transaction> transactionList = transactionService.parseUploadedFiles(files, new User());
        verify(transactionRepository, times(1)).saveAll(transactionList);
    }

    @Test
    @DisplayName("Return Statistics for calling getTransactionsStatistics()")
    void ReturnStatistics_ForCallingGetTransactionsStatistics() {
        when(transactionRepository.findAll()).thenReturn(List.of(transactionToSave));
        when(transactionRepository.findFirstByOrderByAmountDesc()).thenReturn(Optional.of(transactionToSave));
        when(transactionRepository.findFirstByOrderByAmountAsc()).thenReturn(Optional.of(transactionToSave));

        assertEquals(1, transactionService.
                getTransactionsStatistics().getTotalNumberOfTransactions());
    }


}