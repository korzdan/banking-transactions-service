package com.springproject.fileparserwebapp.repos;

import com.springproject.fileparserwebapp.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllByUserId(Long id);
    File saveFile(File file);
}
