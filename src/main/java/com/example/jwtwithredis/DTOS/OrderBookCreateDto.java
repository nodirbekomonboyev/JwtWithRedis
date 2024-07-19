package com.example.jwtwithredis.DTOS;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class OrderBookCreateDto {
    private UUID bookId;
    private Integer count;
}
