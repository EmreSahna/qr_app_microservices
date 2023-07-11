package com.emresahna.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {
    private String fullName;
    private String password;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Card> card;
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
