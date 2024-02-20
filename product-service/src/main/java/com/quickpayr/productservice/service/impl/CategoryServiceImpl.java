package com.quickpayr.productservice.service.impl;

import com.quickpayr.productservice.dto.CategoryRequest;
import com.quickpayr.productservice.dto.CategoryResponse;
import com.quickpayr.productservice.entity.Category;
import com.quickpayr.productservice.mapper.CategoryMapper;
import com.quickpayr.productservice.repository.CategoryRepository;
import com.quickpayr.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = CategoryMapper.mapCategoryRequestToCategory(categoryRequest);

        Category savedCategory = categoryRepository.save(category);

        return CategoryMapper.mapCategoryToCategoryResponse(savedCategory);
    }

    protected List<Category> getProductCategories(List<Long> categoryIds) {
        return categoryRepository.findAllById(categoryIds);
    }

}
