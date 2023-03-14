package com.emresahna.customerservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "customer_transaction")
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

