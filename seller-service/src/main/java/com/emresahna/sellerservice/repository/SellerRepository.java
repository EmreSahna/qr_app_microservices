package com.emresahna.sellerservice.repository;

import com.emresahna.sellerservice.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, String> {
}
