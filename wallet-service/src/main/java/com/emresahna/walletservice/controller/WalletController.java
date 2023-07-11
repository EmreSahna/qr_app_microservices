package com.emresahna.walletservice.controller;

import com.emresahna.walletservice.dto.BalanceRequest;
import com.emresahna.walletservice.dto.CreateWalletRequest;
import com.emresahna.walletservice.entity.Wallet;
import com.emresahna.walletservice.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody CreateWalletRequest createWalletRequest) {
        return ResponseEntity
                .ok()
                .body(walletService.createWallet(createWalletRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Wallet> getWalletByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(walletService.getWalletByUserId(userId));
    }

    @PostMapping("/add-balance")
    public ResponseEntity<Wallet> addBalance(@RequestBody BalanceRequest balanceRequest) {
        return ResponseEntity.ok(walletService.addBalance(balanceRequest));
    }

    @PostMapping("/decrement-balance")
    public ResponseEntity<Wallet> decrementBalance(@RequestBody BalanceRequest balanceRequest) {
        return ResponseEntity.ok(walletService.decrementBalance(balanceRequest));
    }

    @GetMapping("/generate-qr/{userId}")
    public ResponseEntity<byte[]> generateQrCode(@PathVariable String userId) {
        byte[] qrCodeBytes = walletService.generateQrCode(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(qrCodeBytes.length);
        return new ResponseEntity<>(qrCodeBytes, headers, HttpStatus.OK);
    }
}
