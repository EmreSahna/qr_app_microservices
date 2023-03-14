package com.emresahna.sellerservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "seller_transaction")
public class SellerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String buyerId;
    private String purchaseItemId;
    private BigDecimal amount;
    private Timestamp createdAt;
}
