package com.emresahna.walletservice.repository;

import com.emresahna.walletservice.entity.SellerWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerWalletRepository extends JpaRepository<SellerWallet, String> {
    SellerWallet findBySellerId(String sellerId);
}
