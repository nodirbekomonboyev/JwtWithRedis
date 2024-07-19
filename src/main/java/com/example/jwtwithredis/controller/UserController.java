package com.example.jwtwithredis.controller;


import com.example.jwtwithredis.DTOS.UserCreateDto;
import com.example.jwtwithredis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @PostMapping
    public String create(@RequestBody UserCreateDto userCreateDto, Principal principal) {
        System.out.println(principal.getName());
        return userService.add(userCreateDto);
    }
}
