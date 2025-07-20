package com.netroutines.librarymanagementsystem.book;

import com.netroutines.librarymanagementsystem.author.AuthorDTO;
import com.netroutines.librarymanagementsystem.genre.GenreDTO;
import com.netroutines.librarymanagementsystem.publisher.PublisherDTO;

import java.time.LocalDateTime;
import java.util.Set;

public record BookDTO(
        Long id,
        String title,
        LocalDateTime creationDate,
        LocalDateTime updatedDate,
        GenreDTO genre,
        PublisherDTO publisher,
        Set<AuthorDTO> authors
) {
}
