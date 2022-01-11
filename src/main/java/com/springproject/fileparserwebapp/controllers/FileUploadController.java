package com.springproject.fileparserwebapp.controllers;

import com.springproject.fileparserwebapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {
    private final TransactionService transactionService;

    @Value("${upload.path}")
    private String UPLOAD_PATH;

    public FileUploadController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/file_upload")
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_PATH + file.getOriginalFilename());
            Files.write(path, bytes);
            transactionService.parseFileAndSaveTransactions(path.toFile());
        }
        return ResponseEntity.ok("File is parsed and transactions are saved");
    }
}
