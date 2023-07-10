package com.emresahna.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductTransactionRequest {
    private Long id;
    private Integer quantity;
    private BigDecimal price;
}
