package com.emresahna.productservice.controller;

import com.emresahna.productservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("productId") Long productId, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(imageService.uploadImage(productId, file));
    }

    @GetMapping(value = "/{imageUrl}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageUrl) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageService.getImage(imageUrl));
    }

}
