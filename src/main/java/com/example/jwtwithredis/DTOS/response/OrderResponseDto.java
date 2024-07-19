package com.example.jwtwithredis.DTOS.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private UUID id;
    private UUID userId;
    private List<OrderBookResponseDto> books;
    private Double totalPrice;
    private String status;
}
