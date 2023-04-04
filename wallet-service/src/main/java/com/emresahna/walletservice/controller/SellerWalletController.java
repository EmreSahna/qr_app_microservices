package com.emresahna.walletservice.controller;

import com.emresahna.walletservice.dto.BalanceRequest;
import com.emresahna.walletservice.dto.SellerIdRequest;
import com.emresahna.walletservice.entity.CustomerWallet;
import com.emresahna.walletservice.entity.SellerWallet;
import com.emresahna.walletservice.service.SellerWalletService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller-wallet")
public class SellerWalletController {
    private final SellerWalletService sellerWalletService;

    public SellerWalletController(SellerWalletService sellerWalletService) {
        this.sellerWalletService = sellerWalletService;
    }

    @PostMapping("/create")
    public ResponseEntity<SellerWallet> createSellerWallet(@RequestBody SellerIdRequest sellerIdRequest) {
        return ResponseEntity.ok(sellerWalletService.createSellerWallet(sellerIdRequest));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<SellerWallet> withdrawBalance(@RequestBody BalanceRequest balanceRequest) {
        return ResponseEntity.ok(sellerWalletService.withdrawBalance(balanceRequest));
    }

    @PostMapping("/add-balance")
    public void addBalance(@RequestBody BalanceRequest balanceRequest) {
        sellerWalletService.addBalance(balanceRequest);
    }

    @GetMapping("/generate-qr-code/{sellerId}")
    public ResponseEntity<byte[]> generateQrCode(@PathVariable String sellerId) {
        byte[] qrCodeBytes = sellerWalletService.generateQrCode(sellerId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(qrCodeBytes.length);
        return new ResponseEntity<>(qrCodeBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/{seller_id}")
    public ResponseEntity<SellerWallet> getSellerWallet(@PathVariable String seller_id) {
        return ResponseEntity.ok(sellerWalletService.getSellerWallet(seller_id));
    }
}
