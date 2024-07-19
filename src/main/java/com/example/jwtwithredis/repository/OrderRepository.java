package com.example.jwtwithredis.repository;


import com.example.jwtwithredis.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity , UUID> {
    List<OrderEntity> getOrderEntitiesByUserId(UUID userId);
}
