package com.emresahna.walletservice.repository;


import com.emresahna.walletservice.entity.CustomerWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerWalletRepository extends JpaRepository<CustomerWallet, String> {
    CustomerWallet findByCustomerId(String customerId);
}
