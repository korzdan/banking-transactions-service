package com.springproject.fileparserwebapp.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class FileUploadService {
    private final Set<String> ALLOWED_FILE_EXTENSIONS = new HashSet<>(Arrays.asList("xml", "csv"));

    public List<MultipartFile> uploadAllowedFiles(MultipartFile ... files) {
        List<MultipartFile> allowedFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            if (isAllowedExtension(file.getOriginalFilename())) {
                allowedFiles.add(file);
            }
        }
        return allowedFiles;
    }

    private boolean isAllowedExtension(String filename) {
        return ALLOWED_FILE_EXTENSIONS.contains(FilenameUtils.getExtension(filename));
    }
}