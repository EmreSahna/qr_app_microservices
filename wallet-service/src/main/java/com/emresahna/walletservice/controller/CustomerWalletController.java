package com.emresahna.walletservice.controller;

import com.emresahna.walletservice.dto.BalanceRequest;
import com.emresahna.walletservice.dto.CustomerWalletRequest;
import com.emresahna.walletservice.entity.CustomerWallet;
import com.emresahna.walletservice.service.CustomerWalletService;
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

    @PostMapping("/decrement-balance")
    public Boolean decrementBalance(@RequestBody BalanceRequest balanceRequest) {
        return customerWalletService.decrementBalance(balanceRequest);
    }

    @GetMapping("/get-balance/{customer_id}")
    public ResponseEntity<CustomerWallet> getBalance(@PathVariable String customer_id) {
        return ResponseEntity.ok(customerWalletService.getBalance(customer_id));
    }
}
