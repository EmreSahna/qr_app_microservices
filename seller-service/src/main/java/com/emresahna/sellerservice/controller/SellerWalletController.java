package com.emresahna.sellerservice.controller;

import com.emresahna.sellerservice.dto.BalanceRequest;
import com.emresahna.sellerservice.dto.SellerIdRequest;
import com.emresahna.sellerservice.entity.SellerWallet;
import com.emresahna.sellerservice.service.SellerWalletService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<SellerWallet> createSellerWallet(@RequestBody SellerIdRequest sellerIdRequest) {
        return ResponseEntity.ok(sellerWalletService.createSellerWallet(sellerIdRequest));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<SellerWallet> withdrawBalance(@RequestBody BalanceRequest balanceRequest) {
        return ResponseEntity.ok(sellerWalletService.withdrawBalance(balanceRequest));
    }

    @PostMapping("/generate-qr-code")
    public ResponseEntity<byte[]> generateQrCode(@RequestBody SellerIdRequest sellerIdRequest) {
        byte[] qrCodeBytes = sellerWalletService.generateQrCode(sellerIdRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(qrCodeBytes.length);
        return new ResponseEntity<>(qrCodeBytes, headers, HttpStatus.OK);
    }
}
