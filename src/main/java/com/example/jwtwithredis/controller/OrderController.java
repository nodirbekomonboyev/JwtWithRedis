package com.example.jwtwithredis.controller;


import com.example.jwtwithredis.DTOS.OrderCreateDto;
import com.example.jwtwithredis.DTOS.response.OrderResponseDto;
import com.example.jwtwithredis.service.OrderBookService;
import com.example.jwtwithredis.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderBookService orderBookService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public OrderResponseDto create (OrderCreateDto dto) {
        return orderService.create(dto);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update-status")
    public String updateStatus (@RequestParam UUID orderId, @RequestParam String status) {
       return orderService.updateStatus(orderId,status);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update-count")
    public String updateCount (@RequestParam UUID orderBookId, @RequestParam Integer count) {
        return orderBookService.update(orderBookId,count);
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("/get-by-user-id")
    public List<OrderResponseDto> getAll (@RequestParam UUID userId) {
        return orderService.getAllByUserId(userId);
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping("get-all")
    public List<OrderResponseDto> getAll() {
        return orderService.getAll();
    }
}
