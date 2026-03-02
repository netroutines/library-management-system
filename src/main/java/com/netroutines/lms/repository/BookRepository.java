package com.netroutines.lms.repository;

import com.netroutines.lms.entity.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph(attributePaths = {"genre", "publisher", "authors"})
    @NotNull
    Page<Book> findAll(@NotNull Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"genre", "publisher", "authors"})
    @NotNull
    Optional<Book> findById(@NotNull Long id);

}
