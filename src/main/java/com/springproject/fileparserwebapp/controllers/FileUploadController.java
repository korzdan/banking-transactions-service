package com.springproject.fileparserwebapp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/file_upload")
    public String startPage() {
        return "upload_file";
    }

    @PostMapping("/file_upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadPath + file.getOriginalFilename());
            Files.write(path, bytes);
        }
        return "upload_file";
    }
}
