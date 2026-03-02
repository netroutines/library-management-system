package com.netroutines.lms.controller.response;

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
