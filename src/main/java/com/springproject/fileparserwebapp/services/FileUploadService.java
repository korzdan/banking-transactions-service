package com.springproject.fileparserwebapp.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class FileUploadService {
    @Value("${upload.path}")
    private String UPLOAD_PATH;
    private final Set<String> ALLOWED_FILE_EXTENSIONS = new HashSet<>(Arrays.asList("xml", "csv"));

    public List<MultipartFile> uploadFiles(MultipartFile ... files) {
        List<MultipartFile> uploadedFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            if (isAllowedExtension(file.getOriginalFilename())) {
                writeFile(file);
                uploadedFiles.add(file);
            }
        }
        return uploadedFiles;
    }

    private boolean isAllowedExtension(String filename) {
        return ALLOWED_FILE_EXTENSIONS.contains(FilenameUtils.getExtension(filename));
    }

    private void writeFile(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_PATH + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}