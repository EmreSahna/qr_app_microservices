package com.quickpayr.productservice.service;

import com.quickpayr.productservice.dto.CategoryRequest;
import com.quickpayr.productservice.dto.CategoryResponse;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
}
