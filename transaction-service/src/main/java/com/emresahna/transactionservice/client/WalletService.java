package com.emresahna.transactionservice.client;

import com.emresahna.transactionservice.dto.BalanceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "wallet-service")
public interface WalletService {

    @PostMapping("/seller-wallet/add-balance")
    void addBalance(@RequestBody BalanceRequest balanceRequest);

    @PostMapping("/customer-wallet/decrement-balance")
    void decrementBalance(@RequestBody BalanceRequest balanceRequest);
}
