package com.emresahna.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private String customerId;
    private String sellerId;
    private List<ProductTransactionRequest> purchasedItems;
    private BigDecimal amount;
}
