package com.example.jwtwithredis.DTOS;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Data
public class UserCreateDto {
    private String name;
    private String username;
    private String password;
    private String role;
    private int age;
}
