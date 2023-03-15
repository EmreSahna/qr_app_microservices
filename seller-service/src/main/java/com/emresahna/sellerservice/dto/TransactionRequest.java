package com.emresahna.sellerservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {
    private String buyer_id;
    private String seller_id;
    private String purchased_item_id;
    private BigDecimal amount;
}
