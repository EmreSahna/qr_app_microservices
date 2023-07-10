package com.emresahna.transactionservice.controller;

import com.emresahna.transactionservice.dto.SellerIdResponse;
import com.emresahna.transactionservice.dto.TransactionRequest;
import com.emresahna.transactionservice.entity.Transaction;
import com.emresahna.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/read-qr")
    public ResponseEntity<SellerIdResponse> readQr(@RequestParam("qrCode") MultipartFile qrPhoto) {
        return ResponseEntity.ok(transactionService.getDataFormQRCode(qrPhoto));
    }

    @PostMapping("/pay")
    public ResponseEntity<Transaction> doPayment(@RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(transactionService.createTransaction(transactionRequest));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String customerId) {
        return ResponseEntity.ok(transactionService.getTransactionsByCustomerId(customerId));
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Transaction>> getSellerTransactions(@PathVariable String sellerId) {
        return ResponseEntity.ok(transactionService.getTransactionsBySellerId(sellerId));
    }
}
