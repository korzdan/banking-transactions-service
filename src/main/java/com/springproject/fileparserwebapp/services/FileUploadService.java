package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.exception.FileWritingException;
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
    private final Set<String> ALLOWED_FILE_EXTENSIONS = new HashSet<>(Arrays.asList("xml", "csv"));
    @Value("${upload.path}")
    private String UPLOAD_FILE_DIR;

    public List<MultipartFile> uploadAllowedFiles(MultipartFile ... files) {
        for (MultipartFile file : files) {
            if (isAllowedExtension(file.getOriginalFilename())) {
                writeMultipartFileOnDisk(file);
            }
        }
        return Arrays.asList(files);
    }

    private boolean isAllowedExtension(String filename) {
        return ALLOWED_FILE_EXTENSIONS.contains(FilenameUtils.getExtension(filename));
    }

    private MultipartFile writeMultipartFileOnDisk(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FILE_DIR + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new FileWritingException("Exception with writing file on the server.");
        }
        return file;
    }
}