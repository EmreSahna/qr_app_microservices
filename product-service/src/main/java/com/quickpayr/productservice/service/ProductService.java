package com.quickpayr.productservice.service;

import com.quickpayr.productservice.dto.ProductRequest;
import com.quickpayr.productservice.dto.ProductResponse;
import com.quickpayr.productservice.entity.Product;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    Product getProduct(Long id);
}
