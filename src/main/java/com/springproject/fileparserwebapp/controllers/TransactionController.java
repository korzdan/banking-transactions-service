package com.springproject.fileparserwebapp.controllers;

import com.springproject.fileparserwebapp.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@PreAuthorize("hasAuthority('execute_command')")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<?> getAllTransactions() {
        return new ResponseEntity<>(transactionService.findAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransaction(@PathVariable Long id) {
        return new ResponseEntity<>(transactionService.findTransactionById(id), HttpStatus.OK);
    }

    @GetMapping("/top")
    public ResponseEntity<?> getTopFiveTransactions() {
        return new ResponseEntity<>(transactionService.getTopFiveTransactions(), HttpStatus.OK);
    }

    @GetMapping("/max")
    public ResponseEntity<?> getMaxAmountTransaction() {
        return new ResponseEntity<>(transactionService.getMaxAmountTransaction(), HttpStatus.OK);
    }

    @GetMapping("/min")
    public ResponseEntity<?> getMinAmountTransaction() {
        return new ResponseEntity<>(transactionService.getMinAmountTransaction(), HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> getTransactionStatistics() {
        return new ResponseEntity<>(transactionService.getTransactionsStatistics(), HttpStatus.OK);
    }
}
