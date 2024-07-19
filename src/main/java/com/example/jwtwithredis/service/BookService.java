package com.example.jwtwithredis.service;


import com.example.jwtwithredis.DTOS.BookCreateDto;
import com.example.jwtwithredis.entity.BookEntity;
import com.example.jwtwithredis.exception.DataAlreadyExistsException;
import com.example.jwtwithredis.exception.DataNotFoundException;
import com.example.jwtwithredis.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookEntity add(BookCreateDto dto) {
        Optional<BookEntity> byName = bookRepository.findByName(dto.getName());
        if(byName.isPresent()) {
            throw new DataAlreadyExistsException("Book already exists with name : "+byName.get().getName());
        }
        BookEntity map = modelMapper.map(dto, BookEntity.class);
        return bookRepository.save(map);
    }

    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }

    public String update(UUID cardId, BookCreateDto book) {
        BookEntity found = bookRepository.findById(cardId).orElseThrow(() -> new DataNotFoundException("Book not found"));
        found.setAuthor(book.getAuthor());
        found.setPage(book.getPage());
        found.setName(book.getName());
        found.setPrice(book.getPrice());
        bookRepository.save(found);
        return "Book updated";
    }

    public String delete(UUID cardId) {
        bookRepository.deleteById(cardId);
        return "Deleted";
    }
}
