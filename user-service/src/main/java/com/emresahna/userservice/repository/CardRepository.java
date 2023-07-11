package com.emresahna.userservice.repository;

import com.emresahna.userservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, String> {
    List<Card> findAllByUserId(String userId);
}
