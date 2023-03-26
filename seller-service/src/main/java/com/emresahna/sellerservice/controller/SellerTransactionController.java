package com.emresahna.sellerservice.controller;

import com.emresahna.sellerservice.dto.TransactionRequest;
import com.emresahna.sellerservice.entity.SellerTransaction;
import com.emresahna.sellerservice.service.SellerTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller-transaction")
public class SellerTransactionController {
    private final SellerTransactionService sellerTransactionService;

    public SellerTransactionController(SellerTransactionService sellerTransactionService) {
        this.sellerTransactionService = sellerTransactionService;
    }

    @PostMapping("/get-payment")
    public ResponseEntity<Boolean> createSellerTransactionWithWallet(@RequestBody TransactionRequest transactionRequest) {
        return ResponseEntity.ok(sellerTransactionService.createSellerTransactionWithWallet(transactionRequest));
    }

    @GetMapping("/get-transaction/{sellerId}")
    public ResponseEntity<List<SellerTransaction>> getSellerTransactions(@PathVariable String sellerId) {
        return ResponseEntity.ok(sellerTransactionService.getTransactions(sellerId));
    }
}
