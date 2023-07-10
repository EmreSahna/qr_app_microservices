package com.emresahna.transactionservice.repository;

import com.emresahna.transactionservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findAllByCustomerId(String customerId);
    List<Transaction> findAllBySellerId(String sellerId);
}
