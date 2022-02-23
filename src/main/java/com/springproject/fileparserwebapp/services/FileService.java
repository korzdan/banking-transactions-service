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

    private FileRepository fileRepository;

    public List<File> findAllFilesUploadedByUser(User user) {
        return fileRepository.findAllByUserId(user.getId());
    }

    public File saveUploadedFile(MultipartFile file) {
        return fileRepository.saveFile(createFile(file));
    }

    private File createFile(MultipartFile file) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new File(file.getOriginalFilename(), currentUser);
    }

}
