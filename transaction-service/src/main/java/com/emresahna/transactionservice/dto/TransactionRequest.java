package com.emresahna.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private String buyer_id;
    private String seller_id;
    private String purchased_item_id;
    private BigDecimal amount;
}
