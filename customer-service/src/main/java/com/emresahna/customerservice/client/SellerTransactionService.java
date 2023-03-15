package com.emresahna.customerservice.client;

import com.emresahna.customerservice.dto.TransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "seller-transaction-service", url = "http://localhost:8082/seller-transaction")
public interface SellerTransactionService {

    @PostMapping("/get-payment")
    ResponseEntity<Boolean> createSellerTransactionWithWallet(@RequestBody TransactionRequest transactionRequest);
}
