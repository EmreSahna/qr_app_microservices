package com.quickpayr.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="categories")
public class Category extends BaseEntity {
    private String name;

    @ManyToMany
    @JsonIgnore
    private List<Product> product;

    @Enumerated(EnumType.ORDINAL)
    private Status isActive;
}
