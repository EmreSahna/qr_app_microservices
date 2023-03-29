package com.emresahna.userservice.service;

import com.emresahna.userservice.dto.LoginRequest;
import com.emresahna.userservice.dto.RegisterSeller;
import com.emresahna.userservice.entity.Seller;
import com.emresahna.userservice.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public record SellerService(SellerRepository sellerRepository) {

    public Seller registerSeller(RegisterSeller registerSeller) {
        return sellerRepository.save(Seller.builder()
                .sellerName(registerSeller.getSeller_name())
                .taxId(registerSeller.getTax_id())
                .phone(registerSeller.getPhone())
                .email(registerSeller.getEmail())
                .accountNumber(registerSeller.getAccount_number())
                .bankDetails(registerSeller.getBank_details())
                .build());
    }

    public Seller loginSeller(LoginRequest loginRequest) {
        return sellerRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
    }

    public Seller getSeller(String customer_id) {
        return sellerRepository.findById(customer_id).orElse(null);
    }

    public String getSellerEmail(String sellerId){
        return getSeller(sellerId).getEmail();
    }
}
