package com.emresahna.userservice.repository;

import com.emresahna.userservice.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, String> {
    Seller findByEmailAndPassword(String email, String account_number);
}
