package com.emresahna.sellerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "seller_wallet")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String sellerId;
    private BigDecimal balance;
}
