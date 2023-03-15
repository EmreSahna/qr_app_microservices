package com.emresahna.sellerservice.controller;

import com.emresahna.sellerservice.dto.SellerRequest;
import com.emresahna.sellerservice.entity.Seller;
import com.emresahna.sellerservice.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Seller> createSeller(@RequestBody SellerRequest seller) {
        return ResponseEntity.ok(sellerService.createSeller(seller));
    }
}