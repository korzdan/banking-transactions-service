package com.springproject.fileparserwebapp.services;

import com.springproject.fileparserwebapp.models.File;
import com.springproject.fileparserwebapp.models.User;
import com.springproject.fileparserwebapp.repos.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public List<File> findAllFilesUploadedByUser(Long userId) {
        return fileRepository.getAllByUserId(userId);
    }

    public File saveUploadedFile(MultipartFile file, User currentUser) {
        return fileRepository.save(createFile(file, currentUser));
    }

    private File createFile(MultipartFile file, User currentUser) {
        return new File(file.getOriginalFilename(), currentUser);
    }

}
