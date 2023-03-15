package com.emresahna.sellerservice.service;

import com.emresahna.sellerservice.dto.SellerRequest;
import com.emresahna.sellerservice.entity.Seller;
import com.emresahna.sellerservice.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public record SellerService(SellerRepository sellerRepository) {

    public Seller createSeller(SellerRequest sellerRequest) {
        return sellerRepository.save(Seller.builder()
                .sellerName(sellerRequest.getSeller_name())
                .taxId(sellerRequest.getTax_id())
                .phone(sellerRequest.getPhone())
                .email(sellerRequest.getEmail())
                .accountNumber(sellerRequest.getAccount_number())
                .bankDetails(sellerRequest.getBank_details())
                .build());
    }
}
