package com.springproject.fileparserwebapp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {
    @Value("${upload.path}")
    private String UPLOAD_PATH;

    public boolean uploadFiles(MultipartFile ... files) {
        for (MultipartFile file : files) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_PATH + "/" + file.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}