package com.emresahna.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "seller")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String sellerName;
    private String password;
    private String taxId;
    private String phone;
    private String email;
    private String accountNumber;
    private String bankDetails;
    private Timestamp createdAt;
}
