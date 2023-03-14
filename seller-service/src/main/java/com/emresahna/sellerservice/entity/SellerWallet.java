package com.emresahna.sellerservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "seller_wallet")
public class SellerWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String sellerId;
    private BigDecimal balance;
}
