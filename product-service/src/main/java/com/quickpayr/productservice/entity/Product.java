package com.quickpayr.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
public class Product extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Long userId;
    @Enumerated(EnumType.ORDINAL)
    private Status isActive;
    @OneToOne
    private Image image;
    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> category;
}
