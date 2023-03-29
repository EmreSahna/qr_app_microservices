package com.emresahna.transactionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service")
public interface UserService {

    @GetMapping("/seller/get-seller-email/{sellerId}")
    String getSellerEmail(@PathVariable String sellerId);
}
