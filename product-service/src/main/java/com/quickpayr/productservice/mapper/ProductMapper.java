package com.quickpayr.productservice.mapper;

import com.quickpayr.productservice.dto.ProductRequest;
import com.quickpayr.productservice.dto.ProductResponse;
import com.quickpayr.productservice.entity.Category;
import com.quickpayr.productservice.entity.Product;
import com.quickpayr.productservice.entity.Status;

import java.util.List;

public class ProductMapper {
    public static ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().doubleValue())
                .categories(product.getCategory().stream().map(Category::getName).toList())
                .build();
    }

    public static Product mapProductRequestToProduct(ProductRequest productRequest, List<Category> categories) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .userId(productRequest.getUserId())
                .isActive(Status.PASSIVE)
                .category(categories)
                .build();
    }
}
