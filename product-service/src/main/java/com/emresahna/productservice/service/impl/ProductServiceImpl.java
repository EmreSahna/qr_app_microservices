package com.emresahna.productservice.service.impl;

import com.emresahna.productservice.dto.ProductRequest;
import com.emresahna.productservice.dto.ProductResponse;
import com.emresahna.productservice.dto.ProductTransactionRequest;
import com.emresahna.productservice.entity.Category;
import com.emresahna.productservice.entity.Image;
import com.emresahna.productservice.entity.Product;
import com.emresahna.productservice.mapper.ProductMapper;
import com.emresahna.productservice.repository.ProductRepository;
import com.emresahna.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryServiceImpl;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        List<Category> categories = categoryServiceImpl.getProductCategories(productRequest.getCategoryIds());

        Product product = ProductMapper.mapProductRequestToProduct(productRequest, categories);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.mapProductToProductResponse(savedProduct);
    }

    protected void setProductImage(Long productId, Image image) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setImage(image);
        productRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public void checkProductsAvabilityAndPrice(ProductTransactionRequest[] products) {
        for (ProductTransactionRequest product : products) {
            Product productFromDb = productRepository.findById(product.getId()).orElseThrow();
            if (productFromDb.getStock() < product.getQuantity() || !productFromDb.getPrice().equals(product.getPrice())) {
                throw new RuntimeException("Product with id " + product.getId() + " is not available or price is not correct");
            }
        }
        productsQuantityUpdate(products);
    }

    public void productsQuantityUpdate(ProductTransactionRequest[] products) {
        for (ProductTransactionRequest product : products) {
            Product productFromDb = productRepository.findById(product.getId()).orElseThrow();
            productFromDb.setStock(productFromDb.getStock() - product.getQuantity());
            productRepository.save(productFromDb);
        }
    }
}
