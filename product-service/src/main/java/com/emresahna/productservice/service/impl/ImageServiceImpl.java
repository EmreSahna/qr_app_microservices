package com.emresahna.productservice.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.emresahna.productservice.entity.Image;
import com.emresahna.productservice.repository.ImageRepository;
import com.emresahna.productservice.service.ImageService;
import com.emresahna.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final AmazonS3 s3Client;
    private final ImageRepository imageRepository;
    private final ProductServiceImpl productServiceImpl;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.base-url}")
    private String baseUrl;

    @Override
    public String uploadImage(Long productId, MultipartFile file) {
        File convertedFile = convertMultiPartToFile(file);
        String fileUrl = file.getOriginalFilename()+ "-" + productId;
        s3Client.putObject(bucketName , fileUrl, convertedFile);

        Image image = Image.builder()
                .url(baseUrl + fileUrl)
                .build();

        imageRepository.save(image);

        productServiceImpl.setProductImage(productId, image);

        return baseUrl + fileUrl;
    }

    private File convertMultiPartToFile(final MultipartFile multipartFile) {
        // convert multipartFile to File
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            return file;
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert multipartFile to File");
        }
    }
}
