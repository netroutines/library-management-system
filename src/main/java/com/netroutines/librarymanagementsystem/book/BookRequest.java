package com.netroutines.librarymanagementsystem.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record BookRequest(
        @NotNull @NotEmpty @Size(max = 50) String title,
        @NotNull Long genreId,
        @NotNull Long publisherId,
        @NotEmpty Set<Long> authorIds
) {
}
