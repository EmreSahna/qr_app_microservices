package com.emresahna.customerservice.repository;

import com.emresahna.customerservice.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, String> {
}
