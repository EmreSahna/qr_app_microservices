package com.quickpayr.productservice.repository;

import com.quickpayr.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
