package com.emresahna.transactionservice.dto;

import com.emresahna.transactionservice.entity.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
