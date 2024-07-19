package com.example.jwtwithredis.repository;


import com.example.jwtwithredis.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findByName(String name);
}
