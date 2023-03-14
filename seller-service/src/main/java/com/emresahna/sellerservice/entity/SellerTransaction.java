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
    private String buyer_id;
    private String purchase_item_id;
    private BigDecimal amount;
    private Timestamp created_at;
}
