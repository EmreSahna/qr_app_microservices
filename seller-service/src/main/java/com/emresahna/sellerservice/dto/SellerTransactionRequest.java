package com.emresahna.sellerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerTransactionRequest {
    private String buyer_id;
    private String purchased_item_id;
    private BigDecimal amount;
}
