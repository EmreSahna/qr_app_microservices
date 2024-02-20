package com.quickpayr.transactionservice.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Embeddable
public class ProductTransactionRequest {
    private Long id;
    private Integer quantity;
    private BigDecimal price;
}
