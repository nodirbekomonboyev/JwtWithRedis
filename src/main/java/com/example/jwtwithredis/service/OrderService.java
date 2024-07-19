package com.example.jwtwithredis.service;


import com.example.jwtwithredis.DTOS.OrderBookCreateDto;
import com.example.jwtwithredis.DTOS.OrderCreateDto;
import com.example.jwtwithredis.DTOS.response.OrderBookResponseDto;
import com.example.jwtwithredis.DTOS.response.OrderResponseDto;
import com.example.jwtwithredis.entity.OrderEntity;
import com.example.jwtwithredis.exception.BadRequestException;
import com.example.jwtwithredis.exception.DataNotFoundException;
import com.example.jwtwithredis.repository.BookRepository;
import com.example.jwtwithredis.repository.OrderRepository;
import com.example.jwtwithredis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.jwtwithredis.entity.OrderStatus.*;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderBookService orderBookService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    public OrderResponseDto create(OrderCreateDto dto) {
        double totalPrice = 0.0;
        for (OrderBookCreateDto book : dto.getBooks()) {
            totalPrice = totalPrice + bookRepository.findById(book.getBookId())
                    .orElseThrow(() -> new DataNotFoundException("Book not found")).getPrice();
        }

        OrderEntity order = new OrderEntity(
                userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found")),
                totalPrice,
                TOOK
        );
        OrderEntity save = orderRepository.save(order);
        List<OrderBookResponseDto> dtos = orderBookService.save(save,dto.getBooks());
        return new OrderResponseDto(save.getId(),save.getUser().getId(),dtos,dtos.stream().mapToDouble(item -> item.getPrice() * item.getCount()).sum(),save.getStatus().name());
    }

    public String updateStatus(UUID orderId, String status) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Order not found with id : " + orderId));
        switch (status) {
            case "TOOK" -> order.setStatus(TOOK);
            case "RETURNED" -> order.setStatus(RETURNED);
            case "FORGOT" -> order.setStatus(FORGOT);
            default -> throw new BadRequestException(status + " status is not present");
        }
        orderRepository.save(order);
        return "Order updated";
    }

    public List<OrderResponseDto> getAllByUserId(UUID userId) {
        return orderRepository.getOrderEntitiesByUserId(userId).stream().map(order -> {
            List<OrderBookResponseDto> dtos = orderBookService.getByOrderId(order.getId());
            return new OrderResponseDto(order.getId(),order.getUser().getId(),dtos,dtos.stream().mapToDouble(dto -> dto.getCount()* dto.getPrice()).sum(),order.getStatus().name());
        }).toList();
    }

    public List<OrderResponseDto> getAll() {
        return orderRepository.findAll().stream().map(order -> {
            List<OrderBookResponseDto> dtos = orderBookService.getByOrderId(order.getId());
            return new OrderResponseDto(order.getId(),order.getUser().getId(),dtos,dtos.stream().mapToDouble(dto -> dto.getCount()* dto.getPrice()).sum(),order.getStatus().name());
        }).toList();
    }
}
