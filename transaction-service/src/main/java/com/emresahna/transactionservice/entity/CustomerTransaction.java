package com.emresahna.transactionservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "customer_transaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String customerId;
    private String sellerId;
    private String purchasedItemId;
    private String status;
    private BigDecimal amount;
    private Timestamp createdAt;
}

