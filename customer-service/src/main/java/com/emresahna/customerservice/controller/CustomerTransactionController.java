package com.emresahna.customerservice.controller;

import com.emresahna.customerservice.dto.CustomerTransactionRequest;
import com.emresahna.customerservice.service.CustomerTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-transaction")
public class CustomerTransactionController {
    private final CustomerTransactionService customerTransactionService;

    public CustomerTransactionController(CustomerTransactionService customerTransactionService) {
        this.customerTransactionService = customerTransactionService;
    }

    @PostMapping("/pay")
    public ResponseEntity<String> doPayment(@RequestBody CustomerTransactionRequest customerTransactionRequest) {
        return ResponseEntity.ok(customerTransactionService.createCustomerTransactionWithWallet(customerTransactionRequest));
    }
}
