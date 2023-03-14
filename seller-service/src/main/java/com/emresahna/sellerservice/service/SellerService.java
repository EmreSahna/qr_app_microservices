package com.emresahna.sellerservice.service;

import com.emresahna.sellerservice.dto.SellerResponse;
import com.emresahna.sellerservice.exception.SellerNotFoundException;
import com.emresahna.sellerservice.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public SellerResponse getSeller(String id) {
        return sellerRepository.findById(id)
                .map(seller -> SellerResponse.builder()
                        .seller_name(seller.getSeller_name())
                        .tax_id(seller.getTax_id())
                        .phone(seller.getPhone())
                        .email(seller.getEmail())
                        .account_number(seller.getAccount_number())
                        .bank_details(seller.getBank_details())
                        .build())
                .orElseThrow(() -> new SellerNotFoundException("Seller not found"));
    }
}
