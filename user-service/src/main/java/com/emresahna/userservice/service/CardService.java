package com.emresahna.userservice.service;

import com.emresahna.userservice.dto.SaveCardRequest;
import com.emresahna.userservice.entity.Card;

import java.util.List;

public interface CardService {
    Card saveCard(SaveCardRequest saveCardRequest);
    List<Card> getCardsByUserId(String userId);
}
