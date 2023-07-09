package com.emresahna.productservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponse {
    private String name;
    private String description;
    private Double price;
    private List<String> categories;
}
