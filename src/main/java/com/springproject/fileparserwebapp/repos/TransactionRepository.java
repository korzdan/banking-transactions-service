package com.springproject.fileparserwebapp.repos;

import com.springproject.fileparserwebapp.models.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
    List<Transaction> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM transactions ORDER BY amount DESC LIMIT 5")
    List<Transaction> getTopFiveTransactions();

    @Query(nativeQuery = true, value = "SELECT * FROM transactions WHERE amount = " +
            "(SELECT MAX(amount) FROM transactions)")
    Optional<Transaction> getMaxAmountTransaction();

    @Query(nativeQuery = true, value = "SELECT * FROM transactions WHERE amount = " +
            "(SELECT MIN(amount) FROM transactions)")
    Optional<Transaction> getMinAmountTransaction();
}
