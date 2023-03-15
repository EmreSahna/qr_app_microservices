package com.emresahna.sellerservice.controller;

import com.emresahna.sellerservice.dto.BalanceRequest;
import com.emresahna.sellerservice.dto.TransactionRequest;
import com.emresahna.sellerservice.service.SellerTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
