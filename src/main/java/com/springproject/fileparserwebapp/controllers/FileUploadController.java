package com.springproject.fileparserwebapp.controllers;

import com.springproject.fileparserwebapp.models.Transaction;
import com.springproject.fileparserwebapp.services.FileUploadService;
import com.springproject.fileparserwebapp.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final TransactionService transactionService;
    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> fileUpload(@RequestParam("files") MultipartFile[] files) {
        List<Transaction> transactionList = transactionService.parseUploadedFiles(
                fileUploadService.uploadAllowedFiles(files)
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionList);
    }
}
