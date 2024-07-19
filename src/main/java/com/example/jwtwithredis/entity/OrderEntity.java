package com.example.jwtwithredis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST)
    private List<OrderBookEntity> books;
    private Double price ;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.TOOK;

    public OrderEntity(UserEntity user, Double price, OrderStatus status) {
        this.user = user;
        this.price = price;
        this.status = status;
    }
}
