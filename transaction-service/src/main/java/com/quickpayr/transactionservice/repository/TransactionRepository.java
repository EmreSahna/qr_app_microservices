package com.quickpayr.transactionservice.repository;

import com.quickpayr.transactionservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findAllByCustomerId(String customerId);
    List<Transaction> findAllBySellerId(String sellerId);
}
