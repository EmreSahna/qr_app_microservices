package com.quickpayr.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerTransactionNotificationRequest {
    private String customer_id;
    private String seller_email;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
