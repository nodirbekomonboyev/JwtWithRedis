package com.example.jwtwithredis.DTOS.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class OrderBookResponseDto {
    private UUID bookId;
    private Double price;
    private Integer count;
}
