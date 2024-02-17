package com.quickpayr.walletservice.controller;

import com.quickpayr.walletservice.dto.BalanceRequest;
import com.quickpayr.walletservice.dto.CreateWalletRequest;
import com.quickpayr.walletservice.entity.Wallet;
import com.quickpayr.walletservice.service.WalletService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Wallet> createWallet(@Valid @RequestBody CreateWalletRequest createWalletRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(walletService.createWallet(createWalletRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Wallet> getWalletByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(walletService.getWalletByUserId(userId));
    }

    @PostMapping("/add-balance")
    public ResponseEntity<Wallet> addBalance(@Valid @RequestBody BalanceRequest balanceRequest) {
        return ResponseEntity.ok(walletService.addBalance(balanceRequest));
    }

    @PostMapping("/decrement-balance")
    public ResponseEntity<Wallet> decrementBalance(@Valid @RequestBody BalanceRequest balanceRequest) {
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
