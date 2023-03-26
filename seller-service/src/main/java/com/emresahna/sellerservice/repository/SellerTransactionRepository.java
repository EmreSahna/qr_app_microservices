package com.emresahna.sellerservice.repository;

import com.emresahna.sellerservice.entity.SellerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerTransactionRepository extends JpaRepository<SellerTransaction, String> {
    List<SellerTransaction> findBySellerId(String sellerId);
}
