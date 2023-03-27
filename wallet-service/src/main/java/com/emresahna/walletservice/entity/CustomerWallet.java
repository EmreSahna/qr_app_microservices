package com.emresahna.walletservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "customer_wallet")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String customerId;
    private BigDecimal balance;
}
