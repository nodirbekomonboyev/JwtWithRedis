package com.example.jwtwithredis.DTOS;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class OrderCreateDto {
    private UUID userId;
    private List<OrderBookCreateDto> books;
}
