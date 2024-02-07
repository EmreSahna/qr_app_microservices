package com.emresahna.notificationservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TransactionEvent {
    private String id;
    private String customerId;
    private String sellerId;
    private List<ProductTransactionRequest> productIds;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
