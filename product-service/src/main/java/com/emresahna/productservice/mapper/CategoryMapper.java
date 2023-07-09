package com.emresahna.productservice.mapper;

import com.emresahna.productservice.dto.CategoryRequest;
import com.emresahna.productservice.dto.CategoryResponse;
import com.emresahna.productservice.entity.Category;
import com.emresahna.productservice.entity.Status;

public class CategoryMapper {
    public static Category mapCategoryRequestToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getName())
                .isActive(Status.PASSIVE)
                .build();
    }

    public static CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .name(category.getName())
                .build();
    }
}
