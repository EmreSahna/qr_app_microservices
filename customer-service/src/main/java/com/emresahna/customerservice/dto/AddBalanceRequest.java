package com.emresahna.customerservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddBalanceRequest {
    private String customerId;
    private BigDecimal amount;
}
