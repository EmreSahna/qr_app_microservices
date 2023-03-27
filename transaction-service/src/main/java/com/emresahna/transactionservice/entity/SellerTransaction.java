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
@Table(name = "seller_transaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String buyerId;
    private String sellerId;
    private String purchaseItemId;
    private BigDecimal amount;
    private Timestamp createdAt;
}
