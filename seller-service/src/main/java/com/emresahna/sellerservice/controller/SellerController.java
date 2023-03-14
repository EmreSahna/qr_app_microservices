package com.emresahna.sellerservice.controller;

import com.emresahna.sellerservice.dto.SellerResponse;
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

    @GetMapping("/{id}")
    public ResponseEntity<SellerResponse> getSeller(@PathVariable String id) {
        return ResponseEntity.ok(sellerService.getSeller(id));
    }
}