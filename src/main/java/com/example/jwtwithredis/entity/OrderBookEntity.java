package com.example.jwtwithredis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderBookEntity extends BaseEntity {
    @ManyToOne
    private OrderEntity order;
    @ManyToOne
    private BookEntity book;
    private Integer count;
    private Double price;
}
