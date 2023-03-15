package com.emresahna.sellerservice.controller;

import com.emresahna.sellerservice.dto.BalanceRequest;
import com.emresahna.sellerservice.dto.SellerWalletRequest;
import com.emresahna.sellerservice.entity.SellerWallet;
import com.emresahna.sellerservice.service.SellerWalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller-wallet")
public class SellerWalletController {
    private final SellerWalletService sellerWalletService;

    public SellerWalletController(SellerWalletService sellerWalletService) {
        this.sellerWalletService = sellerWalletService;
    }

    @PostMapping("/create")
    public ResponseEntity<SellerWallet> createSellerWallet(@RequestBody SellerWalletRequest sellerWalletRequest) {
        return ResponseEntity.ok(sellerWalletService.createSellerWallet(sellerWalletRequest));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<SellerWallet> withdrawBalance(@RequestBody BalanceRequest balanceRequest) {
        return ResponseEntity.ok(sellerWalletService.withdrawBalance(balanceRequest));
    }
}
