package com.example.jwtwithredis.DTOS;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class BookCreateDto {
    private String author;
    private String name;
    private Integer page;
    private Double price;
}
