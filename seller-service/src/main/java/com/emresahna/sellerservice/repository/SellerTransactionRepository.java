package com.emresahna.sellerservice.repository;

import com.emresahna.sellerservice.entity.SellerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerTransactionRepository extends JpaRepository<SellerTransaction, String> {
}
