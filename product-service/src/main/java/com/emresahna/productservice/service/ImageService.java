package com.emresahna.productservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ImageService {
    String uploadImage(Long productId, MultipartFile file);
    byte[] getImage(String url);
}
