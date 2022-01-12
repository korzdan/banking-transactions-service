package com.springproject.fileparserwebapp.repos;

import com.springproject.fileparserwebapp.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
    List<Transaction> findAll();
}
