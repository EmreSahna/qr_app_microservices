package com.emresahna.customerservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerTransactionRequest {
    private String customer_id;
    private String seller_id;
    private String purchased_item_id;
    private BigDecimal amount;
}
