package com.quickpayr.productservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(Long productId, MultipartFile file);
    byte[] getImage(String url);
}
