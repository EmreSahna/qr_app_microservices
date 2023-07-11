package com.emresahna.userservice.dto;

import lombok.Data;

@Data
public class SaveCardRequest {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expiryDate;
    private String userId;
}
