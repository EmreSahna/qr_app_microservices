package com.emresahna.productservice.service.impl;

import com.emresahna.productservice.dto.CategoryRequest;
import com.emresahna.productservice.dto.CategoryResponse;
import com.emresahna.productservice.entity.Category;
import com.emresahna.productservice.mapper.CategoryMapper;
import com.emresahna.productservice.repository.CategoryRepository;
import com.emresahna.productservice.service.CategoryService;
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
