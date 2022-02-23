package com.springproject.fileparserwebapp.repos;

import com.springproject.fileparserwebapp.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllByUserId(Long id);
    File saveFile(File file);
}
