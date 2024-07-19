package com.example.jwtwithredis.controller;

import com.example.jwtwithredis.DTOS.AuthDto;
import com.example.jwtwithredis.DTOS.UserCreateDto;
import com.example.jwtwithredis.DTOS.response.JwtResponse;
import com.example.jwtwithredis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public String auth (@RequestBody UserCreateDto dto) {
        return userService.add(dto);
    }


    @PostMapping("/sign-in")
    public JwtResponse test (@RequestBody AuthDto dto) {
        return userService.signIn(dto);
    }
}
