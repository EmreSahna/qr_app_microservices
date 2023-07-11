package com.emresahna.userservice.service.impl;

import com.emresahna.userservice.dto.SaveCardRequest;
import com.emresahna.userservice.entity.Card;
import com.emresahna.userservice.entity.User;
import com.emresahna.userservice.mapper.CardMapper;
import com.emresahna.userservice.repository.CardRepository;
import com.emresahna.userservice.service.CardService;
import com.emresahna.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final UserService userService;

    @Override
    public Card saveCard(SaveCardRequest saveCardRequest) {
        Card card = CardMapper.mapSaveCardRequestToCard(saveCardRequest);
        User user = userService.getUser(saveCardRequest.getUserId());
        card.setUser(user);
        return cardRepository.save(card);
    }

    @Override
    public List<Card> getCardsByUserId(String userId) {
        return cardRepository.findAllByUserId(userId);
    }
}
