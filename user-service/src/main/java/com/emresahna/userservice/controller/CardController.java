package com.emresahna.userservice.controller;

import com.emresahna.userservice.dto.SaveCardRequest;
import com.emresahna.userservice.entity.Card;
import com.emresahna.userservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    @PostMapping("/add")
    public ResponseEntity<Card> saveCard(@RequestBody SaveCardRequest saveCardRequest){
        return ResponseEntity.ok(cardService.saveCard(saveCardRequest));
    }
}
