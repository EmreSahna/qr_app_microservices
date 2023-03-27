package com.emresahna.transactionservice.repository;

import com.emresahna.transactionservice.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, String> {
    List<CustomerTransaction> findAllByCustomerId(String customerId);
}
