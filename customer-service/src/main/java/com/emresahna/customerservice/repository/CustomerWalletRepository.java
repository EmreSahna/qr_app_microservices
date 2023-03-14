package com.emresahna.customerservice.repository;

import com.emresahna.customerservice.entity.CustomerWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerWalletRepository extends JpaRepository<CustomerWallet, String> {
    CustomerWallet findByCustomerId(String customerId);
}
