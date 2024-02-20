package com.quickpayr.productservice.controller;

import com.quickpayr.productservice.dto.CategoryRequest;
import com.quickpayr.productservice.dto.CategoryResponse;
import com.quickpayr.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.createCategory(categoryRequest));
    }
}
