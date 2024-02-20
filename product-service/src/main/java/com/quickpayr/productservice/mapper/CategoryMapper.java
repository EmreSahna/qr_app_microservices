package com.quickpayr.productservice.mapper;

import com.quickpayr.productservice.dto.CategoryRequest;
import com.quickpayr.productservice.dto.CategoryResponse;
import com.quickpayr.productservice.entity.Category;
import com.quickpayr.productservice.entity.Status;

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
