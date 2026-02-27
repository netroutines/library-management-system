package com.netroutines.lms.author;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthorRequest(
        @NotNull @NotEmpty @Size(max = 50) String firstName,
        @NotNull @NotEmpty @Size(max = 50) String lastName
) {
}
