package com.quickpayr.walletservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateWalletRequest {
    @NotBlank(message = "User Id must not be null.")
    private String userId;
    @NotNull(message = "Balance must not be null.")
    @DecimalMin(value = "0.0", message = "Balance must be higher than zero.")
    private BigDecimal balance;
}
