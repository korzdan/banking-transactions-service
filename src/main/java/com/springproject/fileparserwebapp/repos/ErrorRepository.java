package com.springproject.fileparserwebapp.repos;

import com.springproject.fileparserwebapp.models.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {
}
