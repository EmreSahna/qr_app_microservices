package com.emresahna.sellerservice.service;

import com.emresahna.sellerservice.dto.LoginSeller;
import com.emresahna.sellerservice.dto.RegisterSeller;
import com.emresahna.sellerservice.entity.Seller;
import com.emresahna.sellerservice.repository.SellerRepository;
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

    public Seller loginSeller(LoginSeller loginSeller) {
        return sellerRepository.findByEmailAndPassword(loginSeller.getEmail(), loginSeller.getPassword());
    }
}
