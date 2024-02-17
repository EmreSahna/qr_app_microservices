package com.quickpayr.walletservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceRequest {
    @NotBlank(message = "User Id must not be null.")
    private String userId;
    @NotNull(message = "Amount must not be null.")
    @DecimalMin(value = "0.0", message = "Amount must be higher than zero.")
    private BigDecimal amount;
}
