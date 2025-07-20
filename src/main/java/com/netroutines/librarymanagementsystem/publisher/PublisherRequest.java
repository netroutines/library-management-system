package com.netroutines.librarymanagementsystem.publisher;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PublisherRequest(@NotNull @NotEmpty @Size(max = 50) String name) {
}
