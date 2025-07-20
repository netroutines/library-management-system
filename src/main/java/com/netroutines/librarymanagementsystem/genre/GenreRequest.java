package com.netroutines.librarymanagementsystem.genre;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GenreRequest(@NotNull @NotEmpty @Size(max = 50) String name) {
}
