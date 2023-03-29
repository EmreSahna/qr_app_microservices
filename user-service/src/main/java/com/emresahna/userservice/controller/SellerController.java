package com.emresahna.userservice.controller;

import com.emresahna.userservice.dto.LoginRequest;
import com.emresahna.userservice.dto.RegisterSeller;
import com.emresahna.userservice.entity.Seller;
import com.emresahna.userservice.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {
    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Seller> registerSeller(@RequestBody RegisterSeller seller) {
        return ResponseEntity.ok(sellerService.registerSeller(seller));
    }

    @PostMapping("/login")
    public ResponseEntity<Seller> loginSeller(@RequestBody LoginRequest seller) {
        return ResponseEntity.ok(sellerService.loginSeller(seller));
    }

    @GetMapping("/get-seller/{sellerId}")
    public ResponseEntity<Seller> getSeller(@PathVariable String sellerId) {
        return ResponseEntity.ok(sellerService.getSeller(sellerId));
    }

    @GetMapping("/get-seller-email/{sellerId}")
    public String getSellerEmail(@PathVariable String sellerId){
        return sellerService.getSellerEmail(sellerId);
    }
}