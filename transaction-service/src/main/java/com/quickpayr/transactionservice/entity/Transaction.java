package com.quickpayr.transactionservice.entity;

import com.quickpayr.transactionservice.dto.ProductTransactionRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String customerId;
    private String sellerId;
    @ElementCollection
    private List<ProductTransactionRequest> productIds;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime lastModifiedAt;
}
