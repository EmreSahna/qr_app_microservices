package com.emresahna.sellerservice.controller;

import com.emresahna.sellerservice.dto.LoginSeller;
import com.emresahna.sellerservice.dto.RegisterSeller;
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

    @PostMapping("/register")
    public ResponseEntity<Seller> registerSeller(@RequestBody RegisterSeller seller) {
        return ResponseEntity.ok(sellerService.registerSeller(seller));
    }

    @PostMapping("/login")
    public ResponseEntity<Seller> loginSeller(@RequestBody LoginSeller seller) {
        return ResponseEntity.ok(sellerService.loginSeller(seller));
    }

    @GetMapping("/get-seller/{sellerId}")
    public ResponseEntity<Seller> getSeller(@PathVariable String sellerId) {
        return ResponseEntity.ok(sellerService.getSeller(sellerId));
    }
}