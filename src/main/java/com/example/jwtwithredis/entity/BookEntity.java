package com.example.jwtwithredis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity extends BaseEntity {
    @Builder.Default
    private Boolean isPresent = true;
    private String author;
    @Column(unique = true)
    private String name;
    private Integer page;
    private Double price;
}
