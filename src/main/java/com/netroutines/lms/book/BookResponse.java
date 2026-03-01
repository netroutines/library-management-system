package com.netroutines.lms.book;

import com.netroutines.lms.author.AuthorResponse;
import com.netroutines.lms.genre.GenreResponse;
import com.netroutines.lms.publisher.PublisherResponse;

import java.time.LocalDateTime;
import java.util.Set;

public record BookResponse(
        Long id,
        String title,
        GenreResponse genre,
        PublisherResponse publisher,
        Set<AuthorResponse> authors,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
