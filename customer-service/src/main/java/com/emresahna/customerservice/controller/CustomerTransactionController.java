package com.emresahna.customerservice.controller;

import com.emresahna.customerservice.dto.CustomerTransactionRequest;
import com.emresahna.customerservice.dto.SellerIdResponse;
import com.emresahna.customerservice.service.CustomerTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/customer-transaction")
public class CustomerTransactionController {
    private final CustomerTransactionService customerTransactionService;

    public CustomerTransactionController(CustomerTransactionService customerTransactionService) {
        this.customerTransactionService = customerTransactionService;
    }

    @PostMapping("/read-qr")
    public ResponseEntity<SellerIdResponse> readQr(@RequestParam("qrCode") MultipartFile qrPhoto) {
        return ResponseEntity.ok(customerTransactionService.getDataFormQRCode(qrPhoto));
    }

    @PostMapping("/pay")
    public ResponseEntity<String> doPayment(@RequestBody CustomerTransactionRequest customerTransactionRequest) {
        return ResponseEntity.ok(customerTransactionService.createCustomerTransactionWithWallet(customerTransactionRequest));
    }
}
