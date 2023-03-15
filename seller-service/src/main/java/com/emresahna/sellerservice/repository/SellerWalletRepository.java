package com.emresahna.sellerservice.repository;

import com.emresahna.sellerservice.entity.SellerWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerWalletRepository extends JpaRepository<SellerWallet, String> {
    SellerWallet findBySellerId(String sellerId);
}
