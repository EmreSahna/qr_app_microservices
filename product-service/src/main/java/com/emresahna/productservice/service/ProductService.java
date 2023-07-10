package com.emresahna.productservice.service;

import com.emresahna.productservice.dto.ProductRequest;
import com.emresahna.productservice.dto.ProductResponse;
import com.emresahna.productservice.dto.ProductTransactionRequest;
import com.emresahna.productservice.entity.Product;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    Product getProduct(Long id);
    void checkProductsAvabilityAndPrice(ProductTransactionRequest[] products);
}
