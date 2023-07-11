package com.emresahna.userservice.mapper;

import com.emresahna.userservice.dto.SaveCardRequest;
import com.emresahna.userservice.entity.Card;

public class CardMapper {
    public static Card mapSaveCardRequestToCard(SaveCardRequest saveCardRequest){
        return Card.builder()
                .cardNumber(saveCardRequest.getCardNumber())
                .cardHolderName(saveCardRequest.getCardHolderName())
                .cvv(saveCardRequest.getCvv())
                .expiryDate(saveCardRequest.getExpiryDate())
                .build();
    }
}
