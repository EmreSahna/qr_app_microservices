package com.emresahna.customerservice.controller;

import com.emresahna.customerservice.dto.BalanceRequest;
import com.emresahna.customerservice.dto.CustomerWalletRequest;
import com.emresahna.customerservice.entity.CustomerWallet;
import com.emresahna.customerservice.service.CustomerWalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-wallet")
public class CustomerWalletController {
    private final CustomerWalletService customerWalletService;

    public CustomerWalletController(CustomerWalletService customerWalletService) {
        this.customerWalletService = customerWalletService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerWallet> createCustomerWallet(@RequestBody CustomerWalletRequest customerWallet) {
        return ResponseEntity.ok(customerWalletService.createCustomerWallet(customerWallet));
    }

    @PostMapping("/add-balance")
    public ResponseEntity<CustomerWallet> addBalance(@RequestBody BalanceRequest balanceRequest) {
        return ResponseEntity.ok(customerWalletService.addBalance(balanceRequest));
    }

    @GetMapping("/get-balance/{customer_id}")
    public ResponseEntity<CustomerWallet> getBalance(@PathVariable String customer_id) {
        return ResponseEntity.ok(customerWalletService.getBalance(customer_id));
    }
}
