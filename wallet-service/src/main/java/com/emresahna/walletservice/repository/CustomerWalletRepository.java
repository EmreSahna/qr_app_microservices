package com.emresahna.walletservice.repository;


import com.emresahna.walletservice.entity.CustomerWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerWalletRepository extends JpaRepository<CustomerWallet, String> {
    Optional<CustomerWallet> findByCustomerId(String customerId);
}
