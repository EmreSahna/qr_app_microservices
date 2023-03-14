package com.emresahna.sellerservice.service;

import com.emresahna.sellerservice.dto.SellerResponse;
import com.emresahna.sellerservice.exception.SellerNotFoundException;
import com.emresahna.sellerservice.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public record SellerService(SellerRepository sellerRepository) {

    public SellerResponse getSeller(String id) {
        return sellerRepository.findById(id)
                .map(seller -> SellerResponse.builder()
                        .seller_name(seller.getSellerName())
                        .tax_id(seller.getTaxId())
                        .phone(seller.getPhone())
                        .email(seller.getEmail())
                        .account_number(seller.getAccountNumber())
                        .bank_details(seller.getBankDetails())
                        .build())
                .orElseThrow(() -> new SellerNotFoundException("Seller not found"));
    }
}
