package com.example.jwtwithredis.controller;


import com.example.jwtwithredis.DTOS.BookCreateDto;
import com.example.jwtwithredis.entity.BookEntity;
import com.example.jwtwithredis.service.BookService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @PreAuthorize(value = "hasRole('LIBRARIAN')")
    @PostMapping("/create")
    public BookEntity create (@RequestBody BookCreateDto dto) {
        return bookService.add(dto);
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping("/update")
    public String updateStatus ( @RequestParam UUID cardId, @RequestParam BookCreateDto book) {
        return bookService.update(cardId,book);
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @DeleteMapping("/delete/{id}")
    private String delete (@PathVariable UUID id) {
        return bookService.delete(id);
    }

    @PermitAll
    @GetMapping
    public List<BookEntity> getAll() {
        return bookService.getAll();
    }
}
