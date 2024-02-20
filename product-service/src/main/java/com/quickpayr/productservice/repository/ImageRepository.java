package com.quickpayr.productservice.repository;

import com.quickpayr.productservice.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
