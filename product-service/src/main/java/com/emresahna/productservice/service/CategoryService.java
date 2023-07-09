package com.emresahna.productservice.service;

import com.emresahna.productservice.dto.CategoryRequest;
import com.emresahna.productservice.dto.CategoryResponse;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
}
