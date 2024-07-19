package com.example.jwtwithredis.service;


import com.example.jwtwithredis.DTOS.OrderBookCreateDto;
import com.example.jwtwithredis.DTOS.response.OrderBookResponseDto;
import com.example.jwtwithredis.entity.BookEntity;
import com.example.jwtwithredis.entity.OrderBookEntity;
import com.example.jwtwithredis.entity.OrderEntity;
import com.example.jwtwithredis.exception.DataNotFoundException;
import com.example.jwtwithredis.repository.BookRepository;
import com.example.jwtwithredis.repository.OrderBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class OrderBookService {
    private final BookRepository bookRepository;
    private final OrderBookRepository orderBookRepository;

    public List<OrderBookResponseDto> save(OrderEntity orderEntity, List<OrderBookCreateDto> books) {
        return books.stream().map(bookDto -> {
            BookEntity entity = bookRepository.findById(bookDto.getBookId())
                    .orElseThrow(() -> new DataNotFoundException("Book not found with id: " + bookDto.getBookId()));
            OrderBookEntity bookEntity1 = new OrderBookEntity(
                    orderEntity,
                    entity,
                    bookDto.getCount(),
                    entity.getPrice()
            );
            return orderBookRepository.save(bookEntity1);
        }).map(book1 ->
                new OrderBookResponseDto(book1.getId(), book1.getPrice(), book1.getCount())).toList();
    }

    public String update(UUID orderBookId, Integer count) {
        OrderBookEntity entity = orderBookRepository.findById(orderBookId)
                .orElseThrow(() -> new DataNotFoundException("Order book not found with id: " + orderBookId));
        entity.setCount(count);
        orderBookRepository.save(entity);
        return "Order book updated";
    }

    public List<OrderBookResponseDto> getByOrderId(UUID orderId) {
        return orderBookRepository.getOrderBookEntitiesByOrderId(orderId).stream().map(entity ->
                new OrderBookResponseDto(entity.getBook().getId(), entity.getPrice(), entity.getCount())).toList();
    }
}
