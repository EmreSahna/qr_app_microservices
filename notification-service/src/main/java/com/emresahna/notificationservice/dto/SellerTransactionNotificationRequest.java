package com.emresahna.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerTransactionNotificationRequest {
    private String customer_id;
    private String seller_email;
    private BigDecimal amount;
    private Timestamp createdAt;
}
