package com.emresahna.customerservice.repository;

import com.emresahna.customerservice.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, String> {
    List<CustomerTransaction> findAllByCustomerId(String customerId);
}
