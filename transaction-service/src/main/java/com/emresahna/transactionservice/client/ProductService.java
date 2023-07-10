package com.emresahna.transactionservice.client;

import com.emresahna.transactionservice.dto.ProductTransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "product-service")
public interface ProductService {

    @PostMapping("/product/check")
    void checkProductsAvabilityAndPrice(@RequestBody ProductTransactionRequest[] products);
}
