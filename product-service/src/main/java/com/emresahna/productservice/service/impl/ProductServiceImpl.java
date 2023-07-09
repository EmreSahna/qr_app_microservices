package com.emresahna.productservice.service.impl;

import com.emresahna.productservice.dto.ProductRequest;
import com.emresahna.productservice.dto.ProductResponse;
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
}
