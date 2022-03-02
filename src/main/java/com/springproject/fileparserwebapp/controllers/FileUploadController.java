package com.springproject.fileparserwebapp.controllers;

import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.services.FileUploadService;
import com.springproject.fileparserwebapp.services.TransactionService;
import com.springproject.fileparserwebapp.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('file_upload')")
    public ResponseEntity<?> fileUpload(@RequestParam("files") MultipartFile[] files) {
        List<MultipartFile> uploadedFiles = fileUploadService.uploadAllowedFiles(files);
        transactionService.saveAllTransactions(
                transactionService.parseUploadedFiles(uploadedFiles, Utils.getCurrentUser()));
        return ResponseEntity.status(HttpStatus.CREATED).body("The files were successfully parsed");
    }
}
