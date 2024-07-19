package com.example.jwtwithredis.repository;


import com.example.jwtwithredis.entity.OrderBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderBookRepository extends JpaRepository<OrderBookEntity, UUID> {
    List<OrderBookEntity> getOrderBookEntitiesByOrderId(UUID orderId);
}
